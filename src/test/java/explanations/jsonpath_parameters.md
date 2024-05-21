###
Parameters that can be passed to new JsonPath:

1-String: The JSON content as a String.
2-File: The JSON content in a file.
3-InputStream: The JSON content as a stream.
4-URL: The JSON content via a URL.
5-Reader: The JSON content via a reader.

    Parameters that can be passed to new JsonPath include:

String: If the JSON content is a String.
File: If the JSON content is stored in a file.
InputStream: If the JSON content is a stream.
URL: If the JSON content is from a URL.
Reader: If the JSON content is from a reader.
Each of these parameters can be used to create a JsonPath object, allowing you to test the JSON content and access various fields.



### 1 Using JSON Response as a String (JSON Response from URI):

    String response = RestAssured.
                            when().
                            get(jsonendpoint).
                            then().
                            extract().body().asString();
    
    JsonPath jsonPath = new JsonPath(response);
Explanation:

Here, you are making an HTTP GET request using RestAssured and receiving the response as a String.
This String JSON response is given as a parameter to the JsonPath object.
The statement new JsonPath(response) creates a JsonPath object to analyze the content of the response.
In this example, the response variable in new JsonPath(response) is the String representation of the JSON response from the URI (https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php). Therefore, the JSON content is passed as a String to new JsonPath.

### 2 Using JSON File as a File:

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
    }
Explanation:

Here, you are storing the JSON content in a file (nestedjson.json).
In the setup method, you specify the path to the file and create a File object.
In the test method, you pass this File object as a parameter to the JsonPath object.
The statement new JsonPath(nestedJson) creates a JsonPath object to analyze the JSON content in the file.
In this example, the JSON content is stored in a file (nestedjson.json). 
The nestedJson variable in new JsonPath(nestedJson) is a reference to the file. 
Therefore, the file is passed as a parameter to new JsonPath.
IntelliJ IDEA new JsonPath Suggestions:
As seen in the screenshot, when you type new JsonPath in IntelliJ IDEA, 
the io.restassured.path.json.JsonPath class is specifically suggested first. 
The parameters that can be passed to new JsonPath are defined within the io.restassured.path.json.JsonPath class. 
When you type new JsonPath in IntelliJ, the options that appear show the types of parameters that the class can accept.

### 3 Reader Example:
Using a Reader to read a JSON file and create a JsonPath object can be done with the FileReader class.


    public class Main {
    public static void main(String[] args) {
    try {
            // Define the file path to the JSON resource
            File jsonFile = new File(System.getProperty("user.dir"), "src/test/resources/json/nestedjson.json");

            // Create a Reader object using FileReader
            Reader reader = new FileReader(jsonFile);

            // Create a JsonPath object using the Reader
            JsonPath jsonPath = new JsonPath(reader);

            // Now you can use jsonPath to access JSON data
            Map<String, Object> projectsMap = jsonPath.getMap("projects");
            List<Map<String, Object>> projectList = (List<Map<String, Object>>) projectsMap.get("project");

            // Access the fields of the first project
            int firstProjectId = (int) projectList.get(0).get("id");
            String firstProjectName = (String) projectList.get(0).get("name");

            // Print the values
            System.out.println("First Project ID: " + firstProjectId);
            System.out.println("First Project Name: " + firstProjectName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
Explanation:

A Reader object is created using the FileReader class.
The statement new JsonPath(reader) creates a JsonPath object using the Reader object.

### 4 InputStream Example:
Using an InputStream to read a JSON file and create a JsonPath object can be done with the FileInputStream class.

    public class Main {
    public static void main(String[] args) {
    try {
            // Define the file path to the JSON resource
            File jsonFile = new File(System.getProperty("user.dir"), "src/test/resources/json/nestedjson.json");

            // Create an InputStream object using FileInputStream
            InputStream inputStream = new FileInputStream(jsonFile);

            // Create a JsonPath object using the InputStream
            JsonPath jsonPath = new JsonPath(inputStream);

            // Now you can use jsonPath to access JSON data
            Map<String, Object> projectsMap = jsonPath.getMap("projects");
            List<Map<String, Object>> projectList = (List<Map<String, Object>>) projectsMap.get("project");

            // Access the fields of the first project
            int firstProjectId = (int) projectList.get(0).get("id");
            String firstProjectName = (String) projectList.get(0).get("name");

            // Print the values
            System.out.println("First Project ID: " + firstProjectId);
            System.out.println("First Project Name: " + firstProjectName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
Explanation:

An InputStream object is created using the FileInputStream class.
The statement new JsonPath(inputStream) creates a JsonPath object using the InputStream object.
Both methods allow you to read the JSON content and create a JsonPath object to access the JSON data.

### 5 URL Parameter Usage
The URL parameter is used in situations where JSON content is retrieved from a web resource (e.g., an API). 
This allows JSON data to be accessed directly via a URL. 
In other words, a URL parameter can be a web address (URL) that returns JSON content.

Example Usage
Let's assume that your JSON data is provided by a web API at the following address: https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php.

You can create a JsonPath object using this URL:

        public class Main {
        public static void main(String[] args) {
        try {
            // Define the URL to the JSON resource
            URL jsonURL = new URL("https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php");

            // Create a JsonPath object using the URL
            JsonPath jsonPath = new JsonPath(jsonURL);

            // Now you can use jsonPath to access JSON data
            Map<String, Object> projectsMap = jsonPath.getMap("projects");
            List<Map<String, Object>> projectList = (List<Map<String, Object>>) projectsMap.get("project");

            // Access the fields of the first project
            int firstProjectId = (int) projectList.get(0).get("id");
            String firstProjectName = (String) projectList.get(0).get("name");

            // Print the values
            System.out.println("First Project ID: " + firstProjectId);
            System.out.println("First Project Name: " + firstProjectName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
Explanation
URL: 
The jsonURL variable represents the web address that returns JSON content.
JsonPath: 
The expression new JsonPath(jsonURL) retrieves the JSON content from jsonURL 
and converts it into a JsonPath object.
Data Access: 
Using the jsonPath object, you can access and retrieve data from the JSON content.
This URL can be any web address that returns JSON content, 
such as a REST API endpoint or a web URL hosting a JSON file. 
This allows you to fetch and process JSON data directly from the internet.


public class JsonPath {
// String constructor
public JsonPath(String json) {
// Implementation
}

    // File constructor
    public JsonPath(File jsonFile) {
        // Implementation
    }

    // InputStream constructor
    public JsonPath(InputStream jsonStream) {
        // Implementation
    }

    // URL constructor
    public JsonPath(URL jsonURL) {
        // Implementation
    }

    // Reader constructor
    public JsonPath(Reader jsonReader) {
        // Implementation
    }
}










