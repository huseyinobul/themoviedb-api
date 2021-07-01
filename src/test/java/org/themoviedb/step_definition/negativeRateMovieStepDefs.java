package org.themoviedb.step_definition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.themoviedb.utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class negativeRateMovieStepDefs {

    Response response;
    String API_KEY = ConfigurationReader.get("api_key");
    String sessionID= ConfigurationReader.get("session_id")+"a";

    @When("user send request to endpoint {string} with invalid {string} or {string} and following valid query params")
    public void user_send_request_to_endpoint_with_invalid_or_and_following_valid_query_params(String endpoint, String sessionId, String apiKey, Map<String,String> parameters) {
        String postBody = "{ \"value\": "+parameters.get("rating_number")+"}";
        int id = Integer.parseInt(parameters.get("id"));

        response= given().contentType(parameters.get("Content-Type")).and()
                .pathParam("movie_id",id)
                .and()
                .queryParam(apiKey,API_KEY)
                .queryParam(sessionId, sessionID)
                .and()
                .body(postBody)
                .post(endpoint);
    }

    @Then("response status code should be {int} and {string} should be {string}")
    public void the_response_status_code_should_be_and_should_be(int statusCode, String statusMessage, String message) {
        String status_message = response.path(statusMessage);

        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(message, status_message);

    }


}
