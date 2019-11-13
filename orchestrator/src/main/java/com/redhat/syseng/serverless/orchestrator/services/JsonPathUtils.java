package com.redhat.syseng.serverless.orchestrator.services;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import com.redhat.syseng.serverless.orchestrator.model.CorrelationToken;
import io.burt.jmespath.Expression;
import io.burt.jmespath.jcf.JcfRuntime;

public class JsonPathUtils {

    public static CorrelationToken getCorrelationToken(String path, JsonObject json) {
        if (json == null || path == null) {
            return null;
        }
        try {
            Expression<Object> expression = new JcfRuntime().compile(path);
            String id = getString((JsonValue) expression.search(json));
            return CorrelationToken.fromString(id);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private static String getString(JsonValue value) {
        if (value instanceof JsonString) {
            return ((JsonString) value).getString();
        }
        return value.toString();
    }
}
