package org.themoviedb.step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.themoviedb.utilities.ConfigurationReader;

import java.util.Map;

public class negativeTopRatedStepDefs {
    Response response;
    String API_KEY = ConfigurationReader.get("api_key") + "sd2";
    String page = "page";
    String language = "language";

    @When("user send request to endpoint {string} with invalid {string} and following query params")
    public void user_request_to_endpoint_with_invalid_and_following_query_params(String endPoint, String apiKey, Map<String, String> queryParams) {
        response = given().accept(ContentType.JSON).and()
                .queryParam(apiKey, API_KEY)
                .queryParam(language, queryParams.get(language))
                .queryParam(page, queryParams.get(page))
                .and()
                .get(endPoint);
    }


    @Then("the response code should be {int}")
    public void the_response_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("the response {string} should be {string}")
    public void the_response_should_be(String statusMessage, String message) {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(message, jsonPath.getString(statusMessage));
    }


}
