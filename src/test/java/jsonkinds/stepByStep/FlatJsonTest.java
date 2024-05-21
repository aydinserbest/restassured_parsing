package jsonkinds.stepByStep;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class FlatJsonTest {

    /*
            {
          "id": 1,
          "name": "A New Project"
             }
     */
    /*
    it is a JSON object

    it is a flat structure where the keys and values are directly within the main object.

    The main object is called root and the keys and values are called properties
    accesses the root of the JSON directly by calling jsonPath.getMap("")


     */

    @Test
    public void test1() {
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
    @Test
    public void test2() {
        // Define the file path to the JSON resource
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/stepbystepjsonfile/one.json");

        JsonPath jsonPath = new JsonPath(jsonExample);
        Map<String, Object> map = jsonPath.getMap("");
        // Access the JSON data
        map.get("id");
        map.get("name");
    }
}

