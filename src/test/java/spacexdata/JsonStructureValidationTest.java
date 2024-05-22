package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class JsonStructureValidationTest {
    /*
    JSON Structure Validation
    1-Verify that the JSON data contains specific keys.
     */
    String endpoint = "https://api.spacexdata.com/v3/launches";
    @Test
    public void testLaunchDataStructure() {
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        for (Map<String, Object> launch : list) {
            assertTrue(launch.containsKey("flight_number"));
            assertTrue(launch.containsKey("mission_name"));
            assertTrue(launch.containsKey("launch_year"));
            assertTrue(launch.containsKey("rocket"));
            assertTrue(launch.containsKey("launch_site"));
            // Add similar checks for other necessary keys.
        }
    }

        //2-Validate the types of certain keys.
        @Test
        public void testLaunchDataTypes () {
            List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
            for (Map<String, Object> launch : list) {
                assertTrue(launch.get("flight_number") instanceof Integer);
                assertTrue(launch.get("mission_name") instanceof String);
                assertTrue(launch.get("launch_year") instanceof String);
                assertTrue(launch.get("rocket") instanceof Map);
                assertTrue(launch.get("launch_site") instanceof Map);
                // Add similar checks for other keys.
            }
        }
    }
