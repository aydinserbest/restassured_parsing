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
        },
        {
        "id": 3,
        "firstName": "mike",
        "lastName": "yellow",
        "email": "mike@gmail.com"
        },
        {
        "id": 4,
        "firstName": "leo",
        "lastName": "parijs",
        "email": "leo@gmail.com"
        },
        {
        "id": 5,
        "firstName": "roni",
        "lastName": "dewy",
        "email": "roni@gmail.com"
        },
        {
        "id": 6,
        "firstName": "hans",
        "lastName": "duur",
        "email": "hans@gmail.com"
        },
        {
        "id": 7,
        "firstName": "carl",
        "lastName": "jep",
        "email": "carl@gmail.com"
        },
        {
        "id": 8,
        "firstName": "Moly",
        "lastName": "yell",
        "email": "molly@gmail.com"
        },
        {
        "id": 9,
        "firstName": "Sean",
        "lastName": "coney",
        "email": "sean@gmail.com"
        },
        {
        "id": 10,
        "firstName": "Keanu",
        "lastName": "reads",
        "email": "keanu@gmail.com"
        },
        {
        "id": 11,
        "firstName": "Michael",
        "lastName": "klop",
        "email": "michael@gmail.com"
        },
        {
        "id": 12,
        "firstName": "Heidi",
        "lastName": "Boer",
        "email": "heidi@gmail.com"
        }
    ] 

When we carefully examine this JSON structure, we can see that it is a JSON array. 
Each element within the array is a singular JSON object. 
Each object contains fields such as "id," "firstName," "lastName," and "email."

JSON Structure Analysis
Array: This JSON structure starts and ends with square brackets [], indicating it is a JSON array.
Objects: Each element within the array is enclosed in curly braces {}, signifying they are JSON objects.
Fields: Each JSON object consists of key-value pairs like "id," "firstName," "lastName," and "email."

Using JsonPath
Let's explore how we can use JsonPath methods to access specific data within this JSON structure.

Retrieving the Entire JSON Structure as a List
We can use the getList() method to obtain all objects within the array as a list.


    @Test
    public void getAllUsersAsList() {
    // Send an HTTP GET request and get the response
    Response response = RestAssured.when().get(jsonEndpoint);

    // Parse the response using JsonPath
    JsonPath jsonPath = response.jsonPath();

    // Retrieve all users as a list
    List<Map<String, Object>> users = jsonPath.getList("$");

    // Print each user
    for (Map<String, Object> user : users) {
        System.out.println(user);
    }
    }

Retrieving a List of Specific Fields
For example, to get a list of all "firstName" fields, we can use the getList() method.
    
    @Test
    public void getAllFirstNames() {
    // Send an HTTP GET request and get the response
    Response response = RestAssured.when().get(jsonEndpoint);

    // Parse the response using JsonPath
    JsonPath jsonPath = response.jsonPath();

    // Retrieve all "firstName" fields as a list
    List<String> firstNames = jsonPath.getList("firstName");

    // Print each "firstName" value
    firstNames.forEach(System.out::println);
    }

Retrieving a Specific Object
We can use the getObject() method to get a specific object in the array. 
For example, to get the first user:

    @Test
    public void getFirstUser() {
    // Send an HTTP GET request and get the response
    Response response = RestAssured.when().get(jsonEndpoint);

    // Parse the response using JsonPath
    JsonPath jsonPath = response.jsonPath();

    // Retrieve the first user as an object
    Map<String, Object> firstUser = jsonPath.getObject("[0]", Map.class);

    // Print the first user
    firstUser.forEach((k, v) -> System.out.println(k + ": " + v));
    }

Retrieving Field Values
We can use methods like getString() and getInt() to get the value of a specific field. 
For example, to get the "firstName" field of the first user:

    @Test
    public void getFirstUserName() {
    // Send an HTTP GET request and get the response
    Response response = RestAssured.when().get(jsonEndpoint);

    // Parse the response using JsonPath
    JsonPath jsonPath = response.jsonPath();

    // Retrieve the "firstName" field of the first user
    String firstName = jsonPath.getString("[0].firstName");

    // Print the "firstName" value
    System.out.println(firstName);
    }

Understanding JSON Structure Before Using JSON Path
Understanding the JSON structure is critical when choosing JsonPath methods. In this example:

We identified that the JSON structure is an array.
We saw that each element in the array is a JSON object.
Using JsonPath, we can retrieve 
        -the entire array, 
        -lists of specific fields, 
        -specific objects, 
        -or specific field values.

Summary
Array: 
The JSON structure is an array indicated by square brackets [].
Objects: 
Each element within the array is a JSON object indicated by curly braces {}.

Using JsonPath: 
Understanding the JSON structure helps guide your choice of JsonPath methods. 
You can choose appropriate JsonPath methods for retrieving arrays, objects, 
and fields (getList(), getObject(), getString(), etc.).
By understanding the JSON structure, you can correctly process the JSON response 
and perform the necessary operations on the data.

Retrieving JSON Data as Map or Domain Objects
When using JsonPath to retrieve JSON data as Map or domain objects, Jackson or Gson library must be included 
in your classpath.

Example Usage
Below is an example demonstrating how to retrieve JSON data as a Map using the Jackson library.

    public class RetrieveResponse {
    String jsonEndpoint = "https://example.com/api/users"; // Replace with a real endpoint

    @Test
    public void getFirstUserAsMap() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the response using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Retrieve the first user as a Map
        Map<String, Object> firstUserMap = jsonPath.getObject("[0]", Map.class);

        // Print the first user
        System.out.println(firstUserMap);
        System.out.println(firstUserMap.get("firstName"));
    }

    @Test
    public void getAllUsersAsList() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the response using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Retrieve all users as a list
        List<Map<String, Object>> users = jsonPath.getList("$");

        // Print each user
        for (Map<String, Object> user : users) {
            System.out.println(user);
        }
    }
    }
Summary

JSON Deserialization: 
When retrieving JSON data as Map or domain objects using JsonPath, you need JSON processing libraries (Jackson, Gson, etc.).

Required Libraries: 
Add the necessary libraries to your Maven project to perform this operation.

Using JsonPath: 
When using JsonPath to retrieve JSON data as Map or domain objects, the JSON processing library must be in your classpath.
By following these steps, you can process JSON data in the desired format and perform operations on it.

Question:
When you use jsonPath.getObject("[0]", Map.class);, you are retrieving the first object as a Map, right?
What if you wanted to retrieve all objects?

Yes, with jsonPath.getObject("[0]", Map.class), we retrieve the first object in the JSON array as a Map. 
If you want to retrieve all objects in the array, you should use the getList() method.

Retrieving All Objects as Maps
To retrieve all objects in the JSON array as Maps, you can follow these steps:

Retrieve All Objects as a List
You can use the getList() method to retrieve all objects in the JSON array as a list.

Example JSON Data
Let's use the above JSON array:

Here is an example showing how to retrieve this JSON array using REST Assured and JsonPath:


    public class RetrieveResponse {
    String jsonEndpoint = "https://example.com/api/users"; // Replace with a real endpoint

    @Test
    public void getAllUsersAsListOfMaps() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the response using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Retrieve all users as a list of Maps
        List<Map<String, Object>> users = jsonPath.getList("$");

        // Print each user
        for (Map<String, Object> user : users) {
            System.out.println(user);
        }
    }
    }
Explanation
JsonPath Expression:  
jsonPath.getList("$") retrieves all objects in the JSON array.
The "$" expression represents the root directory and selects the entire JSON array.

Result: This expression returns a List of Map<String, Object> objects. Each Map represents an object in the JSON array.

Detailed Analysis
List<Map<String, Object>>: 
This structure returns a list of Map objects, where each Map represents an object in the JSON array.

Map<String, Object>: 
Each Map represents the fields (key-value pairs) in a JSON object.
This method allows you to retrieve all objects in the JSON array as a list. 
Each element of the list is a Map representing a JSON object. This way, you can process all objects at once.