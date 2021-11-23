package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetBookHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private bookdao bookdao = new bookdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        List<bookmodel> bookmodels = bookdao.findAll();

        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(bookmodels).build();
    }
}
