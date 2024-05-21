package jsonkinds.differentkindofjsons;

import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NestedJsonParseTest {
    /*
                {
          "projects": {
            "project": [
              {
                "id": 1,
                "name": "A New Project"
              },
              {
                "id": 3,
                "name": "the new name"
              }
            ]
          }
        }

     */
    File nestedJson;
    @Before
    public void setup() {
        // Load the source file
        nestedJson = new File(System.getProperty("user.dir"), "src/test/resources/json/nestedjson.json");
    }
    @Test
    public void testNestedJson() {
        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(nestedJson);

        // Get the map at the path "projects"
        // In this JSON structure, "projects" is a nested object containing the "project" array.
        Map<String, Object> projectsMap = jsonPath.getMap("projects");

        // Print the map to see its structure (for debugging purposes)
        System.out.println(projectsMap);

        // Extract the "project" array from the "projects" map
        // Here, projectsMap.get("project") returns a list of maps
        List<Map<String, Object>> projectList = (List<Map<String, Object>>) projectsMap.get("project");

        // Verify the "id" value of the first project
        assertEquals(1, projectList.get(0).get("id"));

        // Verify the "name" value of the first project
        assertEquals("A New Project", projectList.get(0).get("name"));

        // Verify the "id" value of the second project
        assertEquals(3, projectList.get(1).get("id"));

        // Verify the "name" value of the second project
        assertEquals("the new name", projectList.get(1).get("name"));

        // Print the "id" value of the first project
        System.out.println(projectList.get(0).get("id"));

        // Print the "name" value of the first project
        System.out.println(projectList.get(0).get("name"));

        // Print the "id" value of the second project
        System.out.println(projectList.get(1).get("id"));

        // Print the "name" value of the second project
        System.out.println(projectList.get(1).get("name"));
    }
}
