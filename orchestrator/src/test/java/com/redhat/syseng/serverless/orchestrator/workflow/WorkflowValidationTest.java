package com.redhat.syseng.serverless.orchestrator.workflow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.serverless.workflow.api.WorkflowManager;
import org.serverless.workflow.impl.manager.WorkflowManagerImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkflowValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"workflows/case-assignment.json", "workflows/case-assignment.yaml"})
    public void vaildateCaseAssignment(String spec) throws IOException, URISyntaxException {
        String workflowDef = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(spec).toURI())));
        WorkflowManager workflowManager = new WorkflowManagerImpl();
        assertTrue(workflowManager.setMarkup(workflowDef).getWorkflowValidator().isValid(), workflowManager.getWorkflowValidator().validate().toString());
    }
}
