package jsonpath.differentkindofjsons;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FlatJsonParseTest {
    /*
            {
          "id": 1,
          "name": "A New Project"
        }
     */
    @Test
    public void testFlatJson() {
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/jsonexample2.json");

        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonExample);

        // Get the map at the root level
        // Since the JSON structure is flat, an empty string ("") as the path will get the entire JSON as a map.
        Map<String, Object> map = jsonPath.getMap("");

        // Verify the "id" value
        assertEquals(1, map.get("id"));

        // Verify the "name" value
        assertEquals("A New Project", map.get("name"));

        // Print the "id" value
        System.out.println(map.get("id"));

        // Print the "name" value
        System.out.println(map.get("name"));
    }
}
