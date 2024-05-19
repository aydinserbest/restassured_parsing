package bddtrader_api;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.Map;

public class ParsingJSON {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void response() {
        // Send an HTTP GET request, get the response, and convert the response body to a string
        Map<String,String> response = RestAssured.
                when().
                get(jsonendpoint).jsonPath().getObject("projects.project[0]", Map.class);
               // get(jsonendpoint).jsonPath().getList("projects.project");
        // get(jsonendpoint).jsonPath().getString("projects.project[0].name");
        System.out.println(response);
        System.out.println(response.get("name"));
    }
    @Test
    public void response2() {
        // Send an HTTP GET request, get the response, and convert the response body to a string
        String response = RestAssured.
                when().
                get(jsonendpoint).then().extract().path("projects.project[0].name");
        System.out.println(response);
    }
}
