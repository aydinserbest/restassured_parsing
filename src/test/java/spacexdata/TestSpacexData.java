package spacexdata;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: this test uses junit 5,
// because with junit 4, assertEquals() is not working unless we add trim().replaceAll("\\s+", " ")
// with junit 4 , assertTrue() is  working
// assertThat() method is working from assertj.core.
// but assertEquals() is not working with junit 4

public class TestSpacexData {
    String baseEndpoint = "https://api.spacexdata.com/v3/launches";


    @Test
    public void filteredResults() {
        String expectedRocketName = "Falcon 1";
        List<Map<String, Object>> list = RestAssured.given().queryParam("rocket_name", expectedRocketName)
                .when().get(baseEndpoint).jsonPath().getList("");

        // Using Stream API to process the list
        list.stream().forEach(launch -> {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
            String actualRocketName = (String) rocket.get("rocket_name");
            assertEquals(expectedRocketName, actualRocketName, "Expected: '" + expectedRocketName + "', but was: '" + actualRocketName + "'");


            // assertTrue("Expected: '" + expectedRocketName + "', but was: '" + actualRocketName + "'", expectedRocketName.equals(actualRocketName));
            // Cleaning up the rocket name
            //String cleanedActualName = actualRocketName.trim().replaceAll("\\s+", " ");

            //assertEquals(expectedRocketName, cleanedActualName, "Expected: '" + expectedRocketName + "', but was: '" + cleanedActualName + "'");

        });
    }

    @Test
    public void testWithTwoFilters() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;

        List<Map<String, Object>> list = RestAssured.
                given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().
                getList("");

        // Using Stream API to process the list
        list.forEach(launch -> {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
            String actualRocketName = (String) rocket.get("rocket_name");
            String actualLaunchYear = (String) launch.get("launch_year");
            assertEquals(rocketName, actualRocketName, "Expected: '" + rocketName + "', but was: '" + actualRocketName + "'");
            assertEquals(String.valueOf(launchYear), actualLaunchYear, "Expected: '" + launchYear + "', but was: '" + actualLaunchYear + "'");
        });
    }

    ;
    //TODO: [0] KULLANIMINA BAK, CHATGPT DE DE VAR
    @Test
    public void testWithTwoFilters2() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        Map<String, Object> map = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getMap("[0]");
        System.out.println(map.get("launch_year"));
        Map<String, Object> rocket = (Map<String, Object>) map.get("rocket");
        rocket.get("rocket_name");
        System.out.println(rocket.get("rocket_name"));


    }
    @Test
    public void testWithTwoFilters3() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        List<String> rocketNames = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getList("rocket.rocket_name");

        // Print all rocket names to verify
        for (String name : rocketNames) {
            System.out.println(name);
        }

        // Verify the rocket names
        assertEquals("Falcon 1", rocketNames.get(0));
    }
    @Test
    public void testWithTwoFilters4() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        List<Map<String,Object>> rocketNames = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getList("rocket");

        // Print all rocket names to verify
        for (Map<String,Object> name : rocketNames) {
            System.out.println(name.get("rocket_name"));
        }

        // Verify the rocket names
       // assertEquals("Falcon 1", rocketNames.get(0));
    }
}
//TODO: BU ACIKLAMAYI DUZENLE
// "" KULLANIMI SART DEGIL JSONDA ISTEDIGIMIZ KEY E -PATHE ERISIMIN ACIKLAMASINI DUZENLE
// VE PROJEDEKI DIGER JSON ORNEKLERINDE UYGULA
// YANI: to navigate directly within the JSON structure using a specific path expression
/*
In JSON arrays, when using JSONPath expressions, sometimes it may be necessary to use the $ symbol or an empty string (""). However, it is also possible to navigate directly within the JSON structure using a specific path expression. This varies according to different scenarios and usage patterns of JSONPath
JSONPath Usage in JSON Arrays
Example 1: Retrieving the Entire JSON Array
Using an empty string ("") or the $ symbol to retrieve the entire JSON array:

java
Copy code
List<Map<String, Object>> users = jsonPath.getList("");
// or
List<Map<String, Object>> users = jsonPath.getList("$");
These expressions retrieve the entire JSON array and add each element to the list as a Map.

Example 2: Retrieving a Specific Field in the JSON Array
Using a specific path expression to access a particular field within the JSON array:

java
Copy code
List<String> rocketNames = jsonPath.getList("rocket.rocket_name");
This expression retrieves the rocket_name field within the rocket object of each element in the JSON array and returns them as a list.

Example JSON and Usage
json
Copy code
[
    {
        "flight_number": 5,
        "mission_name": "RazakSat",
        "rocket": {
            "rocket_id": "falcon1",
            "rocket_name": "Falcon 1"
        }
    },
    {
        "flight_number": 2,
        "mission_name": "John",
        "rocket": {
            "rocket_id": "falcon2",
            "rocket_name": "Falcon 1"
        }
    }
]
Retrieving the Entire JSON Array
java
Copy code
List<Map<String, Object>> jsonArray = jsonPath.getList("");
// or
List<Map<String, Object>> jsonArray = jsonPath.getList("$");
These usages retrieve the entire JSON array and add each element to the list as a Map.

Retrieving a Specific Field
java
Copy code
List<Map<String, Object>> rockets = jsonPath.getList("rocket");
This usage retrieves the rocket object within each element of the JSON array and returns them as a list.

Retrieving More Specific Fields
java
Copy code
List<String> rocketNames = jsonPath.getList("rocket.rocket_name");
This usage retrieves the rocket_name field within the rocket object of each element in the JSON array and returns them as a list.

Summary and Conclusion
When using JSONPath, different methods can be used depending on the structure of the path expression and the data you want to access. Here are some important points:

Retrieving the Entire JSON Array: You can use an empty string ("") or the $ symbol to retrieve the entire JSON array.
Retrieving a Specific Field: You can use a specific path expression to access a particular field within the JSON array.
Path Expression Usage: JSONPath expressions follow the hierarchy within the JSON data and allow you to access specific keys or fields.
Both methods can be appropriate in certain scenarios, and which one you should use depends on the data you want to access.
 */




