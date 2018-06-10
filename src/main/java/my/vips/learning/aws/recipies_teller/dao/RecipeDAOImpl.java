package my.vips.learning.aws.recipies_teller.dao;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import my.vips.learning.aws.recipies_teller.Recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vipinsharma on 09/06/18.
 */

public class RecipeDAOImpl implements RecipeDAO {

    private static final String REGION = "us-east-1";
    private AmazonDynamoDB client;
    {
         client = AmazonDynamoDBClientBuilder.standard()
                 .withRegion(REGION)
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }

    public Recipe getRecipeWithCode(String recipeCode){
        Map<String,AttributeValue> resultantMap = getRecipeWithCodeNumber(recipeCode);
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);
        return dynamoDBMapper.marshallIntoObject(Recipe.class,resultantMap);
    }

    private Map<String,AttributeValue> getRecipeWithCodeNumber(String code) {
        Map<String,String> expressionAttributesNames = new HashMap<String,String>();
        expressionAttributesNames.put("#code","recipeCode");
        Map<String,AttributeValue> expressionAttributeValues = new HashMap<String,AttributeValue>();
        expressionAttributeValues.put(":codeValue",new AttributeValue().withS("001"));
        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#code = :codeValue")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);
        QueryResult queryResult = client.query(queryRequest);
        return queryResult.getItems().get(0);
    }
}
