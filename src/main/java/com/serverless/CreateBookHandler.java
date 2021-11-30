package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class CreateBookHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private bookdao bookdao = new bookdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> origin = new HashMap<>();
        origin.put("Access-Control-Allow-Origin", "*");
        try {
            bookmodel book = mapper.readValue((String) input.getBody(), bookmodel.class);
            bookdao.insert(book);
            return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(200).setObjectBody(book).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(500).setObjectBody(input).build();
    }
}
