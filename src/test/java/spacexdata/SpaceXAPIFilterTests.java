package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SpaceXAPIFilterTests {
    private final String baseEndpoint = "https://api.spacexdata.com/v3/launches";

    @Test
    public void testFilteredLaunches() {
        String rocketName = "Falcon 1";
        int expectedNumberOfFilteredItems = 5;

        // Fetch the filtered data from the API using query parameters
        List<Map<String, Object>> list = RestAssured
                .given()
                .queryParam("rocket_name", rocketName)
                .when()
                .get(baseEndpoint)
                .jsonPath()
                .getList("");

        // Assert that the number of returned items matches the expected count
        assertEquals(String.valueOf(expectedNumberOfFilteredItems), list.size(), "The number of filtered launches does not match the expected count");

        // Assert that each item in the response has the rocket_name "Falcon 1"
        for (Map<String, Object> launch : list) {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
            String actualRocketName = (String) rocket.get("rocket_name");
            assertEquals(rocketName, actualRocketName, "The rocket_name does not match 'Falcon 1'");
        }
    }
}
/*
Explanation
Base Endpoint:

private final String baseEndpoint = "https://api.spacexdata.com/v3/launches";
This sets the base endpoint URL for the SpaceX API.

Test Method:

    String rocketName = "Falcon 1";
    int expectedNumberOfFilteredItems = 5;
Here, rocketName specifies the rocket name to filter by,
and expectedNumberOfFilteredItems is the expected count of results.

Using Query Parameters:

List<Map<String, Object>> list = RestAssured
    .given()
    .queryParam("rocket_name", rocketName)
    .when()
    .get(baseEndpoint)
    .jsonPath()
    .getList("");
This sends a GET request to the base endpoint with the query parameter rocket_name set to the specified value.
The response is then parsed into a list of maps.

Assertions:

assertEquals(expectedNumberOfFilteredItems, list.size(), "The number of filtered launches does not match the expected count");
This checks that the number of returned items matches the expected count for the given rocket name.

Verify Rocket Name:

for (Map<String, Object> launch : list) {
    Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
    String actualRocketName = (String) rocket.get("rocket_name");
    assertEquals(rocketName, actualRocketName, "The rocket_name does not match 'Falcon 1'");
}
This loop checks that each returned item has the correct rocket_name.

By running this test, you can verify that the API correctly filters launches by the rocket_name
and returns the expected results.
 */
