Understanding the type of data that will be returned when using JSONPath methods that start with get 
and choosing the appropriate method is very important. 
Determining which method to use based on the structure of the JSON data ensures 
that you get the correct type of data. Here's a summary to explain this approach more clearly:

Using JSONPath Methods and the Types of Data They Return
For Object:
If there is an object indicated by {} in the JSON data, we use the getMap() or getObject() methods 
to retrieve this object.
For example:

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
In this structure, "projects" is a JSON object. 
Therefore, we use getMap("projects") or getObject("projects", Map.class).

For Array:
If there is an array indicated by [] in the JSON data, we use the getList() method to retrieve this array.
For example:

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
In this structure, the JSON data is directly an array. Therefore, we use getList("$") or getObject("", List.class).

For Array Within an Object:
To access an array within an object in the JSON data, the getList() method is also used.
For example:

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
In this structure, to access the "project" array within the "projects" object, we use getList("projects.project").

This summary has taught us how to use JSONPath methods and determine the type of data that will be returned. 
Below are test methods that apply these principles:

Example Code


    public class MethodsOfJsonPath {

    String jsonEndpoint1 = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php"; // JSON object example
    String jsonEndpoint2 = "https://example.com/api/json2"; // JSON array example

    @Test
    public void gettingObjectUsingGetObjectMethod() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint1).
                jsonPath();

        // Get the "projects" object
        Map<String, Object> projects = jsonPath.getObject("projects", Map.class);

        // Directly get the "project" array within the projects object
        List<Map<String, Object>> projectList = jsonPath.getList("projects.project");

        // Verify the name of the first project
        String firstProjectName = projectList.get(0).get("name").toString();
        assertEquals("A New Project", firstProjectName);
    }

    @Test
    public void gettingArrayUsingGetObjectMethod() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint2).
                jsonPath();

        // Directly get the entire JSON array
        List<Map<String, Object>> users = jsonPath.getList("$");

        // Verify the name of the first user
        String firstName = users.get(0).get("firstName").toString();
        assertEquals("brew", firstName);

        // Verify the email of the second user
        String secondEmail = users.get(1).get("email").toString();
        assertEquals("john@gmail.com", secondEmail);
    }
    }
In these code examples, we showed how to retrieve data using JSONPath's getObject() and getList() methods, 
according to the structure of the JSON data. 
This ensures that the JSON data is processed correctly and in the correct type.