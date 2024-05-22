package jsonkinds.stepByStep;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class FlatJsonTest1 {

    /*
            {
          "id": 1,
          "name": "A New Project"
             }
     */
    /*
    -it is a JSON object
    -Root Object: This JSON structure itself is the root object.
        A key named "id" and its value exist.
        A key named "name" and its value exist.

    -it is a flat structure where the keys and values are directly within the main object.

    -The main object is called root

    -If there is an object indicated by {} in the JSON data, we use the getMap() or getObject() methods
    to retrieve this object.

    -this JSON structure is considered flat and does not contain nested objects or arrays,
    each key is directly at the root level. This allows you to access it by calling jsonPath.getMap("").

    -The jsonPath.getMap("") accesses the root of the JSON directly and assigns it to the map variable.

    -Flat Structure: The JSON structure is simple, with direct key-value pairs at the root level.
    -No Nested Objects/Arrays: The JSON does not contain any nested structures.
    -Accessing Root Level Data: You can access all the key-value pairs at the root level by calling jsonPath.getMap("").
    -This is why the following code works for a flat JSON structure:
    -Map<String, Object> map = jsonPath.getMap("");
    This code retrieves all the data at the root level of the JSON as a Map<String, Object>

    -Since the JSON structure is flat and does not contain any nested objects or arrays,
    all key-value pairs are directly at the root level, we use jsonPath.getMap("") call
    to retrieve all the key-value pairs at the root level

    -If the JSON structure is nested we need to specify the path to the nested element to retrieve the data.
    e.g. To get the "project" array inside "projects", we use the path "projects.project".
    but here we have a flat structure so we use an empty string-"" as the path.

     */

    @Test
    public void test1() {
        // Define the file path to the JSON resource
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/stepbystepjsonfile/one.json");

        JsonPath jsonPath = new JsonPath(jsonExample);
        // Since the JSON structure is flat and contains no nested objects or arrays,
        // an empty string ("") as the path will get the entire JSON as a map.
        Map<String, Object> map = jsonPath.getMap("");
        // Access the JSON data
        map.get("id");
        map.get("name");

    }
    @Test
    public void test2() {
        // Define the file path to the JSON resource
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/stepbystepjsonfile/one.json");

        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonExample);
        // Access the JSON data
        jsonPath.getString("id");
        jsonPath.getString("name");
    }
}

