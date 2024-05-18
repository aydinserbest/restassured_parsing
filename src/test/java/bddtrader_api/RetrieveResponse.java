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
    public void response() {
        // Send an HTTP GET request, get the response, and convert the response body to a string
        String response = RestAssured.
                when().
                get(jsonendpoint).
                asString(); // Convert the entire response body to a String
        // Use the JsonPath library to parse the response
        JsonPath jsonPath = new JsonPath(response);
        // Access the name field of the first project in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }

    @Test
    public void response2() {
        // Send an HTTP GET request and get the response as a Response object
        Response response = RestAssured.
                when().
                get(jsonendpoint);
        // Parse the JSON response directly from the Response object
        JsonPath jsonPath = response.jsonPath();
        // Access the name field of the first project in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }

    @Test
    public void response3() {
        // Send an HTTP GET request and get the response as a Response object
        Response response = RestAssured.
                when().
                get(jsonendpoint).andReturn();

        // Convert the response body to a string
        String responseBody = response.getBody().asString();

        // Use JsonPath.from() to parse the JSON response from the string
        JsonPath jsonPath = JsonPath.from(responseBody);
        // Access the name field of the first project in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }

    @Test
    public void response4() {
        // Send an HTTP GET request and get the response as a Response object
        Response response = RestAssured.
                when().
                get(jsonendpoint).andReturn();

        // Parse the JSON response directly from the Response object
        JsonPath jsonPath = response.jsonPath();
        // Access the name field of the first project in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
        /*
        Explanation of the Differences:
    1-Test response():

    This test converts the entire response body to a string using asString().
    It then creates a new JsonPath object from that string.
    This method demonstrates converting the response to a string first, then parsing it.

    2-Test response2():

    This test gets the response as a Response object and directly parses the JSON from the response using response.jsonPath().
    This method is more direct and avoids converting the response to a string first.

    3-Test response3():

    This test gets the response as a Response object and then converts the response body to a string.
    It then uses JsonPath.from() to parse the JSON from that string.
    This method shows an alternative way of parsing by first converting the response to a string and then using JsonPath.from().

    4-Test response4():

    This test is similar to response2(). It gets the response as a Response object and directly parses the JSON from the response using response.jsonPath().
    The andReturn() method is used here to explicitly return the Response object, although it's not strictly necessary as get() implicitly returns the Response.

    Summary:
    response(): Converts the response to a string first, then parses it.
    response2(): Directly parses the response without converting it to a string.
    response3(): Converts the response to a string, then uses JsonPath.from() to parse it.
    response4(): Similar to response2(), directly parses the response, with the use of andReturn() for explicit Response return.
    These small differences illustrate the flexibility of REST Assured and JsonPath in handling and parsing JSON responses in various ways.
             */

    /*
    Difference Between JsonPath.from() and new JsonPath()
    these two lines of code are not exactly the same, though they achieve similar results.
    They represent different ways of parsing a JSON response using the JsonPath library.


    JsonPath.from(responseBody):

    Usage: This is a static method in the JsonPath class.
    Purpose: It is used to parse a JSON response from a string.
    Example:

    // Convert the response body to a string
    String responseBody = response.getBody().asString();

    // Use JsonPath.from() to parse the JSON response from the string
    JsonPath jsonPath = JsonPath.from(responseBody);
    new JsonPath(response):

    Usage: This is a constructor in the JsonPath class.
    Purpose: It is used to create a JsonPath object directly from a string.
    Example:

    // Use the JsonPath library to parse the response
    JsonPath jsonPath = new JsonPath(response);

    Detailed Explanation:
    JsonPath.from(String):

    This method is used to parse a JSON string and create a JsonPath object from it. It is a static method that provides a convenient way to parse a JSON response directly from a string.
    Example Usage:

    // Convert the response body to a string
    String responseBody = response.getBody().asString();

    // Parse the JSON response using JsonPath.from()
    JsonPath jsonPath = JsonPath.from(responseBody);
    new JsonPath(String):

    This is a constructor of the JsonPath class, which takes a JSON string as an argument and creates a JsonPath object from it.
    Example Usage:

    // Use the JsonPath constructor to parse the response
    JsonPath jsonPath = new JsonPath(response);

    Practical Example:
    Consider a REST API that returns a JSON response.
    Let's compare the two methods in the context of a REST Assured test.
    */

    @Test
    public void testJsonPathFrom() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonendpoint);

        // Convert the response body to a string
        String responseBody = response.getBody().asString();

        // Use JsonPath.from() to parse the JSON response from the string
        JsonPath jsonPath = JsonPath.from(responseBody);

        // Access a specific field in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }

    @Test
    public void testJsonPathConstructor() {
        // Send an HTTP GET request and get the response as a string
        String response = RestAssured.when().get(jsonendpoint).asString();

        // Use the JsonPath constructor to parse the response
        JsonPath jsonPath = new JsonPath(response);

        // Access a specific field in the JSON response
        System.out.println(jsonPath.getString("projects.project[0].name"));
    }
}


    /*
    Summary:
    JsonPath.from(String):
    A static method that parses a JSON string and returns a JsonPath object.
    It is often used after converting the response body to a string.
    new JsonPath(String): A constructor that directly creates a JsonPath object from a JSON string.
    While both methods ultimately create a JsonPath object for querying JSON data,
    they provide different ways to achieve the same result.
    The static method JsonPath.from() can be more readable and concise in certain contexts,
    whereas the constructor approach might feel more intuitive to some users.
     */