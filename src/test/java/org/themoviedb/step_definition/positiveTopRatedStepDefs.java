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

public class positiveTopRatedStepDefs {
    Response response;
    String API_KEY = ConfigurationReader.get("api_key");
    String page="page";
    String language="language";

    @Given("check The API with valid {string} and the API should be available")
    public void check_the_api_with_valid_and_the_api_should_be_available(String apiKey) {
        response = given().queryParam(apiKey, API_KEY).and().get("/");
        Assert.assertEquals(404, response.statusCode());
    }

    @When("user send request to endpoint {string} with valid {string} and following query params")
    public void user_request_to_endpoint_with_valid_and_following_query_params(String endpoint, String apiKey, Map<String,String> queryParams) {
        response = given().accept(ContentType.JSON).when().queryParam(apiKey, API_KEY)
                .queryParam(language, queryParams.get(language))
                .queryParam(page, queryParams.get(page))
                .and().get(endpoint);

    }

    @Then("the response code should be {int} and page number should be {int}")
    public void the_response_code_should_be_and_page_number_should_be(int statusCode, int pageNumber) {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(pageNumber, Integer.parseInt(jsonPath.getString(page)));
    }

}
