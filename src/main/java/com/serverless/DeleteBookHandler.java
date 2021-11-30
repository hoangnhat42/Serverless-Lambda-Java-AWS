package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class DeleteBookHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private bookdao bookdao = new bookdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> origin = new HashMap<>();
        origin.put("Access-Control-Allow-Origin", "*");
        try {
            Map<String, String> pathParameters = input.getPathParameters();
            String id = pathParameters.get("id");
            bookmodel book = bookdao.findById(id);
            bookdao.delete(book);
            return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(200).setObjectBody(true).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(500).setObjectBody(false).build();
    }
}
