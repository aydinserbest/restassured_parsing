package bddtrader_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

public class RetrieveResponse {
    /*

    1-REST Assured allows you to make HTTP requests and receive the responses.
    2-The data returned by a web service in response to an HTTP request can be formatted as JSON, XML, or other formats.
    3-REST Assured is capable of handling both JSON and XML responses.
    4-A JSON response is the data returned by a web service or API in JSON format in response to an HTTP request.
    5-XmlPath is a REST Assured supplied library for parsing XML
    6-JsonPath is a REST Assured supplied library for parsing JSON
    7-JSON parsing is the process of converting JSON data into a format that can be processed by the program.
    8-XML parsing is the process of converting XML data into a format that can be processed by the program.
    9-Accessing Fields:
        The expression jsonPath.getString("projects.project[0].name") accesses a specific field in the JSON response using GPath notation.
    10-Validation:
    The assertThat(name, equalTo("A New Projectaniheeiadtatd")) statement checks if the obtained value matches the expected value.
     */
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void response(){
        // Send an HTTP GET request and get the response and convert the response body to a string
        String response = RestAssured.
                when().
                get(jsonendpoint).
                asString();
        // Use the JsonPath library
        JsonPath jsonPath = new JsonPath(response);
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
    @Test
    public void response2(){
        // Send an HTTP GET request and get the response
        Response response = RestAssured.
                when().
                get(jsonendpoint);
        // Parse the JSON response
        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
    @Test
    public void response3(){
        // Send an HTTP GET request and get the response
        Response response = RestAssured.
                when().
                get(jsonendpoint).andReturn();

        // Convert the response body to a string
        String responseBody = response.getBody().asString();

        // Parse the JSON response
        JsonPath jsonPath = JsonPath.from(responseBody);

        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
    @Test
    public void response4(){
        // Send an HTTP GET request and get the response
        Response response = RestAssured.
                when().
                get(jsonendpoint).andReturn();

        // Parse the JSON response
        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
}
