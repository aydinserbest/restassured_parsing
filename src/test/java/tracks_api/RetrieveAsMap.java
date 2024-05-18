package tracks_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RetrieveAsMap {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

    @Test
    public void testMap() {
        Response response = RestAssured.
                when().
                get(jsonendpoint);
        String string = response.body().asString();

        JsonPath jsonPath = new JsonPath(string);

        Map<Object, Object> maps = jsonPath.getMap("projects.project[0]");
        System.out.println(maps.get("name"));
    }

    @Test
    public void testMap2() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonendpoint).andReturn();

        // Convert the response body to a string
        String responseBody = response.getBody().asString();

        // Parse the JSON response
        JsonPath jsonPath = new JsonPath(responseBody);

        // Extract a list of maps where each map is a project
        List<Map<String, Object>> projects = jsonPath.getList("projects.project");

        // Print the list of maps
        System.out.println(projects);

        // Optionally, assert the size of the list to ensure it has 6 projects
        assertEquals(6, projects.size());
    }

    @Test
    public void testExtractNameKeyValuePairs() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonendpoint).andReturn();

        // Convert the response body to a string
        String responseBody = response.getBody().asString();

        // Parse the JSON response
        JsonPath jsonPath = JsonPath.from(responseBody);

        // Extract a list of project maps
        List<Map<String, Object>> projects = jsonPath.getList("projects.project");

        // Create a list of maps where each map contains the key-value pair for "name"
        List<Map<String, String>> nameKeyValuePairs = projects.stream()
                .map(project -> Map.of("name", (String) project.get("name")))
                .toList();
        System.out.println(nameKeyValuePairs.get(0).get("name"));

        // Print the list of name key-value pairs
        nameKeyValuePairs.forEach(System.out::println);

        // Optional: Assert the size of the list
        assert nameKeyValuePairs.size() == 6 : "Expected 6 projects, but got " + nameKeyValuePairs.size();
    }
}
