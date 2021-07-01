package org.themoviedb.step_definition;

import io.cucumber.java.Before;
import org.themoviedb.utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;

public class Hooks {

    @Before
    public void setUp(){
        baseURI = ConfigurationReader.get("baseURI");
    }
}
