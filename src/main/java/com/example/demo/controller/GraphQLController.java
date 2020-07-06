package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Map<String, Object> graphql(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        if (jsonObject != null) {
            return executeGraphqlQuery(jsonObject.getString("query"), jsonObject.getString("operationName"), jsonObject.getJSONObject("variables"));
        }
        return null;
    }

    private Map<String, Object> executeGraphqlQuery(String query, String operationName, Map<String, Object> variables) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .operationName(operationName)
                .variables(variables)
                .build();
        return this.graphQL.execute(executionInput).toSpecification();
    }
}
