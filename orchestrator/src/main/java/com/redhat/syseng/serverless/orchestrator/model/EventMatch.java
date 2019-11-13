package com.redhat.syseng.serverless.orchestrator.model;

import javax.json.JsonObject;

import com.redhat.syseng.serverless.orchestrator.services.JsonPathUtils;
import org.serverless.workflow.api.events.EventTrigger;

public class EventMatch {

    public final CorrelationToken token;
    public final EventTrigger triggerDef;
    public final JsonObject data;

    public EventMatch(EventTrigger t, JsonObject data) {
        this.token = JsonPathUtils.getCorrelationToken(t.getCorrelationToken(), data);
        this.data = data;
        this.triggerDef = t;
    }
}
