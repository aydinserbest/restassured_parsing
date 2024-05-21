    {
    "id": 1,
    "name": "A New Project"
    }

JSON Structure
The given JSON example:

    {
    "id": 1,
    "name": "A New Project"
    }
Root Concept
In this JSON structure, the root is the top-level structure of the JSON data. 
In this case, the root is the entire structure { "id": 1, "name": "A New Project" }. 
That means the JSON object itself is the root.

Code Explanation

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

Explanation of jsonPath.getMap(""); with the Root Concept

jsonPath.getMap("") retrieves all the data at the root level of the JSON structure and converts it into a Map.
Using an empty string ("") in this context means retrieving all key-value pairs at the root level of the JSON document.

Summary
Root: The root is the top-level structure of the JSON document. 
In this example, the entire structure { "id": 1, "name": "A New Project" } is the root.

jsonPath.getMap(""): 
This statement retrieves all data at the root level and converts it into a Map<String, Object>. 
Because we are at the root level, the keys can be accessed directly as "id" and "name".
Visualization
The root of the JSON document: { "id": 1, "name": "A New Project" }
The statement jsonPath.getMap("") retrieves all the data at the root level and assigns it to the map variable.
map.get("id") and map.get("name") retrieve the values associated with the "id" and "name" keys at the root level.
This way, jsonPath.getMap("") allows you to easily access all the data at the root level of the JSON document.

###

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

Let's explain the root concept in the given JSON example:

JSON Example

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

Root Concept
In this JSON structure, the root refers to the top-level structure of the JSON document.

Top Level: In this JSON structure, the top level is defined by an array. 
That means the data enclosed in the [] square brackets represents the root level of this JSON document.
Root: In this case, the root is the JSON array. Each element within this array is a JSON object.

Summary
The root of this JSON structure is the array defined by the [] square brackets.
This array contains two JSON objects:
First object: {"id": 2, "firstName": "brew", "lastName": "more", "email": "brew@gmail.com"}
Second object: {"id": 1, "firstName": "john", "lastName": "white", "email": "john@gmail.com"}
Visualization

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
In this structure, the root concept can be explained as follows:

Root: Represents the array.
Content of the Root: The two JSON objects contained within the array.
In this case, the root array is the top-level structure of the JSON data, 
and each element within it is defined as a JSON object.

###

    {
    "id": 1,
    "name": "A New Project"
    } 

To explain the root concept, we first need to understand the general structure of the JSON.

Root Concept
Root: The top-level structure of the JSON data. The root is the main container where the JSON document starts, 
and it encloses all other data within it.

Root Concept on an Example
The given JSON structure:

    {
    "id": 1,
    "name": "A New Project"
    }
This structure represents the entirety of the JSON data. So, what is the root in this case?

Top Level:
The top level of the JSON data is the object defined by the {} curly braces. 
This JSON object is considered the root.
The root is the outermost container of the JSON data, and in this example, 
it is the entire structure { "id": 1, "name": "A New Project" }.
Root Object:
In this example, the root object is { "id": 1, "name": "A New Project" }. 
That means the top-level JSON object is called the root.
Root in a Larger JSON Structure
Let's explain the root concept in a larger JSON structure:

    {
    "courses": {
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
    },
    "projects": {
    "project": [
    {
    "id": 2,
    "name": "Another Project"
    }
    ]
    },
    "instructor": "RahulShetty"
    }
In this example:

The object defined by the {} curly braces at the top level is the root.
The root object contains the keys "courses", "projects", and "instructor".
Summary
In your given example:

    {
    "id": 1,
    "name": "A New Project"
    }
The root of this JSON structure is the entire { "id": 1, "name": "A New Project" } structure.
The root is the top-level JSON object and encloses all other data.
In larger JSON structures, the root is similarly defined as the top-level container object. 
When analyzing or processing JSON data, the root object is the starting point for the entire structure, 
and all other data structures are contained within this root object.

###

below two lines are related to the 'root' term:

    -getObject("", List.class); // Using "" to retrieve the entire JSON array
and

    -getList("$"); // Using "$" to retrieve the entire JSON array


public class MethodsOfJsonPath {

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
    String jsonEndpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void gettingObjectUsingGetObjectMethod() {
        // Retrieve the JSON array using getObject() method
        List<Map<String, Object>> users = RestAssured.
                when().
                get(jsonEndpoint).
                jsonPath().
                getObject("", List.class); // Using "" to retrieve the entire JSON array

        // Verify the first user's name
        String firstName = (String) users.get(0).get("firstName");
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = (String) users.get(1).get("email");
        assertEquals("john@gmail.com", secondEmail);
    }

    @Test
    public void gettingObjectUsingGetMapMethod() {
        // Retrieve the JSON array using getMap() method
        List<Map<String, Object>> users = RestAssured.
                when().
                get(jsonEndpoint).
                jsonPath().
                getList("$"); // Using "$" to retrieve the entire JSON array

        // Verify the first user's name
        String firstName = (String) users.get(0).get("firstName");
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = (String) users.get(1).get("email");
        assertEquals("john@gmail.com", secondEmail);
    }
    }

JSONPath allows specifying the root directory using both "" (empty string) and "$". 
These two expressions are interchangeable, meaning you can use either one. 
Let's explain with examples:

**getList() Method**
- `getList("$")`
- `getList("")`
  Both usages retrieve the JSON array and specify the root directory.

**getObject() Method**
- `getObject("", List.class)`
- `getObject("$", List.class)`
  Both usages retrieve the JSON array and specify the root directory.

**Example Code**
To demonstrate that both usages work correctly, 
let's adjust the code examples:


    public class MethodsOfJsonPath {

    String jsonEndpoint1 = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php"; // JSON object example
    String jsonEndpoint2 = "https://example.com/api/json2"; // JSON array example

    @Test
    public void gettingObjectUsingGetObjectMethodWithEmptyString() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint2).
                jsonPath();

        // Retrieve the entire JSON array using getObject("", List.class)
        List<Map<String, Object>> users = jsonPath.getObject("", List.class);

        // Verify the first user's name
        String firstName = users.get(0).get("firstName").toString();
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = users.get(1).get("email").toString();
        assertEquals("john@gmail.com", secondEmail);
    }

    @Test
    public void gettingObjectUsingGetObjectMethodWithDollarSign() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint2).
                jsonPath();

        // Retrieve the entire JSON array using getObject("$", List.class)
        List<Map<String, Object>> users = jsonPath.getObject("$", List.class);

        // Verify the first user's name
        String firstName = users.get(0).get("firstName").toString();
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = users.get(1).get("email").toString();
        assertEquals("john@gmail.com", secondEmail);
    }

    @Test
    public void gettingArrayUsingGetListMethodWithEmptyString() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint2).
                jsonPath();

        // Retrieve the entire JSON array using getList("")
        List<Map<String, Object>> users = jsonPath.getList("");

        // Verify the first user's name
        String firstName = users.get(0).get("firstName").toString();
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = users.get(1).get("email").toString();
        assertEquals("john@gmail.com", secondEmail);
    }

    @Test
    public void gettingArrayUsingGetListMethodWithDollarSign() {
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint2).
                jsonPath();

        // Retrieve the entire JSON array using getList("$")
        List<Map<String, Object>> users = jsonPath.getList("$");

        // Verify the first user's name
        String firstName = users.get(0).get("firstName").toString();
        assertEquals("brew", firstName);

        // Verify the second user's email
        String secondEmail = users.get(1).get("email").toString();
        assertEquals("john@gmail.com", secondEmail);
    }
    }

In this code, you can see that 
both getObject("", List.class) and getObject("$", List.class) as well as getList("") and getList("$") methods 
produce the same result. 
Therefore, both methods can be used to specify the root directory.

###

What is Meant by "Since the JSON structure is flat"
The phrase "Since the JSON structure is flat" means that the JSON structure is simple and not nested. 
This implies that the JSON data consists of direct key-value pairs 
and does not contain nested structures or complex data types.

Flat JSON Structure
Given JSON structure:

    {
    "id": 1,
    "name": "A New Project"
    }
This structure is considered flat because it consists of only key-value pairs 
and does not contain nested objects or arrays. In other words, each key is directly at the root level.

Code Explanation

    // Get the map at the root level
    // Since the JSON structure is flat, an empty string ("") as the path will get the entire JSON as a map.
    Map<String, Object> map = jsonPath.getMap("");
Since the JSON structure is flat: This indicates that the JSON structure is simple and flat, 
meaning it is composed of key-value pairs directly at the root level.
An empty string ("") as the path will get the entire JSON as a map: 
This means that by using jsonPath.getMap(""), you can retrieve all the data at the root level as a Map. 
Because the JSON structure is flat, you can access all the key-value pairs at the root level this way.
Relation to Root
Yes, this is related to the concept of the root. Being at the root level means that the JSON data is 
at the topmost level, and this makes the structure flat. 
Flat structures are composed of direct key-value pairs at the root level without nested structures. 
Therefore, data at the root level can be easily accessed using jsonPath.getMap("").

In summary, "Since the JSON structure is flat" indicates that the JSON structure is simple and not nested. 
This means that all data at the root level can be directly accessed, 
making it easy to retrieve the entire JSON as a map.


Because the JSON structure is considered flat and does not contain nested objects or arrays, 
each key is directly at the root level. 
This allows you to access it by calling jsonPath.getMap("").

Summary
Flat Structure: The JSON structure is simple, with direct key-value pairs at the root level.
No Nested Objects/Arrays: The JSON does not contain any nested structures.
Accessing Root Level Data: You can access all the key-value pairs at the root level by calling jsonPath.getMap("").
This is why the following code works for a flat JSON structure:


    Map<String, Object> map = jsonPath.getMap("");
This code retrieves all the data at the root level of the JSON as a Map<String, Object>


