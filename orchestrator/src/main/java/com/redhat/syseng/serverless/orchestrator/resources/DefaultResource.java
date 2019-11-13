package com.redhat.syseng.serverless.orchestrator.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.redhat.syseng.serverless.orchestrator.services.ProcessService;
import com.redhat.syseng.serverless.orchestrator.services.WorkflowService;
import io.cloudevents.CloudEvent;
import io.cloudevents.v1.AttributesImpl;
import io.cloudevents.v1.CloudEventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class DefaultResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResource.class);

    @Inject
    ProcessService processService;

    @Inject
    WorkflowService workflowService;

    @Inject
    Validator validator;

    @POST
    public CompletionStage<Response> receiveEvent(CloudEvent<AttributesImpl, Object> event) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.debug("New event received: {} - {}", event.getAttributes().getId(), event.getData().get());
            workflowService.getEventMatches(event.getAttributes().getSource(),
                                            event.getAttributes().getType(),
                                            toJson(event.getData()))
                .stream()
                .forEach(processService::receive);
            return Response.accepted().build();
        });
    }

    @POST
    @Path("/test")
    public CompletionStage<CloudEvent<AttributesImpl, Object>> test(CloudEvent<AttributesImpl, Object> event) {
        return CompletableFuture.supplyAsync(() -> {
            CloudEvent<AttributesImpl, Object>  result = CloudEventBuilder.builder(event).withValidator(validator).withId(UUID.randomUUID().toString()).build();
            return result;
        });
    }

    private static Optional<JsonObject> toJson(Optional<Object> object) {
        if(object.isPresent()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(Json.createObjectBuilder((Map<String, Object>)object.get()).build());
    }

    @GET
    @Path("/instances/{version}")
    public CompletionStage<Response> getInstances(@PathParam("version") String version) {
        return CompletableFuture.supplyAsync(() -> Response.ok(processService.instances(version)).build());
    }
}