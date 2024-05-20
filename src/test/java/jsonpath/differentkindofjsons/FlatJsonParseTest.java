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
    /*
        In this JSON structure:

        "id": Key, 1: Value
        "name": Key, "A New Project": Value
     */
    /*
        Key: The name of a field in a JSON object.
                For example, "id", "name", "projects", "project".
        Value: The data corresponding to a key. Values can be of various types:
                number (1, 3),
                string ("A New Project"),
                object ({"project": [...]}),
                or array ([...]).
     */

    /*
        This JSON structure defines a simple JSON object.
        In general, the {} symbols represent an object in JSON.
        Object: A collection of key-value pairs. For example, {"id": 1, "name": "A New Project"}

        Here, "id" and "name" are keys within this object,
        and their values are simple data types (integer and string, respectively).
        This is a flat structure where the keys and values are directly within the main object.

        the value after the : can be a simple data types (integer and string).
                              another JSON object
                              or any other data type.
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
