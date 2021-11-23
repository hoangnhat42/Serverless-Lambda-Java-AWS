package com.serverless;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import connection.DynamoDBAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bookdao {
    private final DynamoDBMapper mapper;

    public bookdao() {
        this.mapper = DynamoDBAdapter.getInstance()
                .createDbMapper(DynamoDBMapperConfig.builder().build());
    }

    public void insert(bookmodel book) {
        mapper.save(book);
    }

    public void update(bookmodel book, String id) {
        mapper.save(book, buildExpression(id));
    }

    private DynamoDBSaveExpression buildExpression(String id) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("id",
                new ExpectedAttributeValue(new AttributeValue().withS(id)));
        dynamoDBSaveExpression.setExpected(expectedAttributeValueMap);
        return dynamoDBSaveExpression;
    }

    public void delete(bookmodel book) {
        mapper.delete(book);
    }

    public List<bookmodel> findAll () {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<bookmodel> results = this.mapper.scan(bookmodel.class, scanExp);
        return results;
    }
}
