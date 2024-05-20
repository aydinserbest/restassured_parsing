package jsonpath.differentkindofjsons;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ArrayJsonParseTest {
    /*
            [
          {
            "id": 2,
            "firstName": "brew",
            "lastName": "more",
            "email": "brew@gmail.com"
          },
          {
            "id": 1,
            "firstName": "john",
            "lastName": "white",
            "email": "john@gmail.com"
          }
        ]

     */
    @Test
    public void testArrayJson() {
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"), "src/test/resources/arrayofobjectsjson.json");

        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonExample);

        // Get the list at the root level
        // Since the JSON structure is an array, an empty string ("") as the path will get the entire JSON as a list.
        List<Map<String, Object>> list = jsonPath.getList("");

        // Verify the "id" value of the first element
        assertEquals(2, list.get(0).get("id"));

        // Verify the "firstName" value of the first element
        assertEquals("brew", list.get(0).get("firstName"));

        // Verify the "lastName" value of the first element
        assertEquals("more", list.get(0).get("lastName"));

        // Verify the "email" value of the first element
        assertEquals("brew@gmail.com", list.get(0).get("email"));

        // Verify the "id" value of the second element
        assertEquals(1, list.get(1).get("id"));

        // Verify the "firstName" value of the second element
        assertEquals("john", list.get(1).get("firstName"));

        // Verify the "lastName" value of the second element
        assertEquals("white", list.get(1).get("lastName"));

        // Verify the "email" value of the second element
        assertEquals("john@gmail.com", list.get(1).get("email"));

        // Print the "id" value of the first element
        System.out.println(list.get(0).get("id"));

        // Print the "firstName" value of the first element
        System.out.println(list.get(0).get("firstName"));

        // Print the "lastName" value of the first element
        System.out.println(list.get(0).get("lastName"));

        // Print the "email" value of the first element
        System.out.println(list.get(0).get("email"));

        // Print the "id" value of the second element
        System.out.println(list.get(1).get("id"));

        // Print the "firstName" value of the second element
        System.out.println(list.get(1).get("firstName"));

        // Print the "lastName" value of the second element
        System.out.println(list.get(1).get("lastName"));

        // Print the "email" value of the second element
        System.out.println(list.get(1).get("email"));
    }
}
