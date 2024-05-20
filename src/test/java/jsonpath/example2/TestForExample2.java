package jsonpath.example2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestForExample2 {
    /*
    {
  "id": 1,
  "name": "A New Project"
}
     */


    @Test
    public void parseJsonFromFile() throws IOException {
        // Load the source file
        File jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/json/jsonexample2.json");

        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonExample);
        // Get the map at the root level
        // Since the JSON structure is flat, an empty string ("") as the path will get the entire JSON as a map.
        Map<String, Object> map = jsonPath.getMap("");
        // Print the "id" value
        System.out.println(map.get("id"));
        // Print the "name" value
        System.out.println(map.get("name"));

        // Verify the "name" value
        assertEquals("A New Project", map.get("name"));

        System.out.println(jsonPath.getString("id"));
        System.out.println(jsonPath.getString("name"));
    }
    /*
    {
        "projects": {
            "project": [
                {
                    "id": 1,
                    "name": "A New Projectaniheeiadtatd"
                },
                {
                    "id": 3,
                    "name": "the new name aniheeiaosono"
                }
                        ]
                    }
    }
}
     */
    @Test
    public void testNestedJson(){
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        // Get the map at the path "projects"
        // In this JSON structure, "projects" is a nested object containing the "project" array.
        Map<String, Object> map = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects");

        // Print the map to see its structure (for debugging purposes)
        System.out.println(map);

        // Get the first element of the "project" array under "projects"
        List<Map<String, Object>> projectList = (List<Map<String, Object>>) map.get("project");
        // Print the "name" value of the first project
        System.out.println(projectList.get(0).get("name"));
        // Print the "id" value of the first project
        System.out.println(projectList.get(0).get("id"));

        // Verify the "name" value of the first project
        assertEquals("A New Projectaniheeiadtatd", projectList.get(0).get("name"));

    }
    @Test
    public void testNestedJsonRoot(){
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonendpoint);

        // Get the map at the path "projects"
        // In this JSON structure, "projects" is a nested object containing the "project" array.
        //so when we write this line, it will not give compile error but when we run it
        //we will have JsonPathException: Failed to parse the JSON document error
        Map<String, Object> projectsMap = jsonPath.getMap("projects");

        //here when we store jsonPath.getMap("projects");
        //it will not return a single object, it has more than 1 object, actually a list
        //1- we can cast it
       // List<Map<String, Object>> projectList = (List<Map<String, Object>>) jsonPath.get("project");
        //or 2- we can use getList()
//        List<Map<String, Object>> project = jsonPath.getList("projects");
//        System.out.println(project.get(0).get("id"));
//        System.out.println(project.get(0).get("name"));


    }
}

