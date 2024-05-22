package jsonkinds.stepByStep;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class FlatJsonTest2 {

    /*
            {
  "projects": {
    ...
  }
}
     */
    /*
        -Nested JSON Structure:    It is a JSON object.

        -A key named "projects" and its value exist.

       - It is a nested structure where the key "projects" contains another object.

        -If there is an object indicated by {} in the JSON data, we use the getMap() or getObject() methods to retrieve this object.

        -This JSON structure is considered nested because it contains an object inside the "projects" key.

        -The "projects" key is directly at the root level, but its value is another object.

        -To access the nested object inside "projects",
        we need to specify the path "projects" when calling jsonPath.getMap().

        -The jsonPath.getMap("projects") accesses the nested object inside the "projects" key
        and returns it as a Map<String, Object> and assigns it to the map variable.

        -Nested Structure: The JSON structure is more complex, with a key-value pair that contains another object.
        No Flat Structure: The JSON contains a nested structure, not a flat one.

        -Accessing Nested Data: You need to specify the path to the nested element to access its key-value pairs.

        -This is why the following code works for a nested JSON structure:
        Map<String, Object> map = jsonPath.getMap("projects");
        This code retrieves all the data inside the "projects" key as a Map<String, Object>.

        -Since the JSON structure is nested and contains an object inside the "projects" key,
        We use the path "projects" to retrieve the key-value pairs inside the "projects" object.
        or we use 'projects' as the path when calling jsonPath.getMap("projects").

     */
    @Test
    public void test2() {
        // Define the file path to the JSON resource
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/stepbystepjsonfile/one.json");

        JsonPath jsonPath = new JsonPath(jsonExample);
        // Since the JSON structure is nested and contains an object inside the "projects" key,
        // we use the path "projects" to retrieve the key-value pairs inside the "projects" object.
        Map<String, Object> map = jsonPath.getMap("projects");
        // Access the JSON data within the nested structure
        // Example: map.get("someKeyInsideProjects");
    }


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
}

