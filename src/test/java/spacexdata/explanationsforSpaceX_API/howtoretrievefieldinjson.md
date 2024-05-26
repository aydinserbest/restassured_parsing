###

1-If your JSON structure directly contains an object,it starts with {,
and you want to retrieve this object, you use getMap()
2-If you want to retrieve the rocket objects within the JSON array, you can use getList() 
because you are retrieving objects within each element of the array

###
Why Does It Differ?
Root Level Array: 
If the JSON data contains an array at the root level, the elements of the array can be accessed directly.
If there is an array at the root level, the elements of the array can be accessed directly using a path expression. 

For example, getList("rocket").

        [
        {
        "flight_number": 5,
        "mission_name": "RazakSat",
        "rocket": {
        "rocket_id": "falcon1",
        "rocket_name": "Falcon 1"
        }
        },
        {
        "flight_number": 2,
        "mission_name": "John",
        "rocket": {
        "rocket_id": "falcon2",
        "rocket_name": "Falcon 1"
        }
        }
        ]


Therefore, you can access the rocket objects directly using the getList("rocket") expression.


WE WANT TO GET AN ELEMENT OF THE ARRAY, USE GETLIST
This JSON structure is an array at the root level. Each rocket object is among the elements of the array.
getList() is used to access the rocket object because each rocket object is among the elements of the array. 
We use getList() because we are retrieving from the array.

///

Root Level Object: If the JSON data contains an object at the root level, If there is an object at the root level,,
the structures within this object need to be specified. 
Therefore, to access the project array, you must specify the projects object.

For example, getList("projects.project")

        {
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    }

###

Accessing the First rocket Object:

If we want to access the rocket object within the first element of the array, we can use the following JSONPath expression:

    // Access the "rocket" object inside the first element of the array
    Map<String, Object> firstRocket = jsonPath.getMap("[0].rocket");
The expression jsonPath.getMap("[0].rocket") retrieves the rocket object within the first element of the JSON array 
and returns it as a Map.

Here, the [0] expression is used to access the first element of the array, 
and then the rocket object within this element is accessed.

Additional Information

If you only want to retrieve the rocket_name fields, you can do so directly using a JSONPath expression:


    public class AccessRocketNames {

    String jsonEndpoint = "https://example.com/api/json3"; // Example endpoint

    @Test
    public void accessRocketNames() {
        // Get the JSON response and parse it using JsonPath
        JsonPath jsonPath = RestAssured.
                when().
                get(jsonEndpoint).
                jsonPath();

        // Access all "rocket_name" fields inside the "rocket" objects
        List<String> rocketNames = jsonPath.getList("rocket.rocket_name");

        // Print all rocket names to verify
        for (String name : rocketNames) {
            System.out.println(name);
        }

        // Verify the rocket names
        assertEquals("Falcon 1", rocketNames.get(0));
        assertEquals("Falcon 1", rocketNames.get(1));
    }
    }


###

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


If we want to access the project array within the projects object, 
we should use the path expression projects.project. 
This expression follows the hierarchical structure of the JSON and allows us to reach the correct data.

Code Example:

    // Access the "project" array within the "projects" object using a JSONPath expression
    List<Map<String, Object>> projectList = jsonPath.getList("projects.project");
Explanation:

The expression jsonPath.getList("projects.project") first reaches the projects object 
and then accesses the project array within this object.
This path expression correctly follows the hierarchy within the JSON structure.
Incorrect Path Usage:

If we use only project in the path expression, it will look for a key named project at the root level of the JSON data. 
However, since there is no such key at the root level, it will not return the correct result.

Code Example:

    // Incorrect path expression usage
    List<Map<String, Object>> projectList = jsonPath.getList("project");
Explanation:

The expression jsonPath.getList("project") looks for a key named project at the root level.
Since there is no key named project at the root level in the JSON data, 
this expression is incorrect and does not return the correct result.
Following the Hierarchical Structure:

JSONPath expressions follow the hierarchical structure of the JSON data to reach the correct data.


###
What is Meant by "Array at the Root Level"?
The term "array at the root level" means that the JSON structure starts with an array ([]) at its outermost (root) level. 
This array contains elements that can be JSON objects or other data types.

Example of an Array at the Root Level
An example of JSON data with an array at the root level:

    [
    {
    "flight_number": 5,
    "mission_name": "RazakSat",
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    },
    {
    "flight_number": 2,
    "mission_name": "John",
    "rocket": {
    "rocket_id": "falcon2",
    "rocket_name": "Falcon 1"
    }
    }
    ]
In this example:

The JSON data starts with an array ([]) at the root level.
The elements of the array are objects ({}).
Example of an Object at the Root Level
In contrast, an example of JSON data with an object at the root level:

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
In this example:

The JSON data starts with an object ({}) at the root level.
Inside this object, there is another object, and within that object, there is an array.
Difference Between Array at the Root Level and Object at the Root Level
Array at the Root Level:

The JSON data starts with an array ([]).
The elements of the array can be objects or other data types.
Direct access to the elements of the array is possible.
Object at the Root Level:

The JSON data starts with an object ({}).
The object can contain other objects, arrays, or other data types.
The keys within the object must be specified to access the data.
JSONPath Usage
For an Array at the Root Level:


    // Accessing the "rocket" objects within the elements of the JSON array
    List<Map<String, Object>> rockets = jsonPath.getList("rocket");
For an Object at the Root Level:

    // Accessing the "project" array within the root level object
    List<Map<String, Object>> projectList = jsonPath.getList("projects.project");
Summary
Array at the Root Level: The JSON data has an array at its outermost level.
Object at the Root Level: The JSON data has an object at its outermost level.

JSONPath expressions allow you to follow the hierarchical structure of the JSON data to access the correct data. 
Therefore, it is important to specify the correct hierarchy in the path expressions.



###

/*
In JSONPath expressions, path expressions are used according to the hierarchical structure of the JSON. 
Therefore, the structure at the root level may differ in JSONPath expressions. 
Let's compare two examples.

Example 1: JSON Array


    [
    {
    "flight_number": 5,
    "mission_name": "RazakSat",
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    },
    {
    "flight_number": 2,
    "mission_name": "John",
    "rocket": {
    "rocket_id": "falcon2",
    "rocket_name": "Falcon 1"
    }
    }
    ]
In this JSON structure, there is an array at the root level. 
Each element of the array contains an object, 
and within these objects, there is a nested object called rocket. 
Therefore, we can directly use the expression getList("rocket") to access the rocket objects.

Code Example:

    List<Map<String, Object>> rockets = jsonPath.getList("rocket");
This expression returns a list of rocket objects within each element of the JSON array.

Example 2: Array within a JSON Object

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
In this JSON structure, 
there is an object at the root level, 
and within this object, there is another object called projects. 
Inside the projects object, there is an array called project. 
Therefore, to access the project array, we need to specify the projects object.

Code Example:

    List<Map<String, Object>> projectList = jsonPath.getList("projects.project");
This expression returns a list of the project array within the projects object.

Why Does It Differ?
Root Level Array: If the JSON data contains an array at the root level, the elements of the array can be accessed directly. 
Therefore, you can access the rocket objects directly using the expression getList("rocket").
Root Level Object: If the JSON data contains an object at the root level, the structures within this object need to be specified. 
Therefore, to access the project array, you must specify the projects object.
Summary
JSON Array: If there is an array at the root level, you can access the elements of the array directly using a path expression. 
For example, getList("rocket").
JSON Object: If there is an object at the root level, the structures within the object need to be specified. 
For example, getList("projects.project").
This difference depends on the structure and hierarchy of the JSON data. 
JSONPath expressions follow this hierarchical structure of the JSON to access the correct data.


###
In JSON arrays, when using JSONPath expressions, sometimes it may be necessary to use the $ symbol or an empty string ("").
However, it is also possible to navigate directly within the JSON structure using a specific path expression.
This varies according to different scenarios and usage patterns of JSONPath

JSONPath Usage in JSON Arrays

Example 1: Retrieving the Entire JSON Array
Using an empty string ("") or the $ symbol to retrieve the entire JSON array:

    List<Map<String, Object>> users = jsonPath.getList("");
    // or
    List<Map<String, Object>> users = jsonPath.getList("$");
These expressions retrieve the entire JSON array and add each element to the list as a Map.

Example 2: Retrieving a Specific Field in the JSON Array
Using a specific path expression to access a particular field within the JSON array:

    List<String> rocketNames = jsonPath.getList("rocket.rocket_name");
This expression retrieves the rocket_name field within the rocket object of each element in the JSON array and returns them as a list.

Example JSON and Usage

    [
    {
    "flight_number": 5,
    "mission_name": "RazakSat",
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    },
    {
    "flight_number": 2,
    "mission_name": "John",
    "rocket": {
    "rocket_id": "falcon2",
    "rocket_name": "Falcon 1"
    }
    }
    ]

Retrieving the Entire JSON Array

    List<Map<String, Object>> jsonArray = jsonPath.getList("");
    // or
    List<Map<String, Object>> jsonArray = jsonPath.getList("$");
These usages retrieve the entire JSON array and add each element to the list as a Map.

Retrieving a Specific Field

    List<Map<String, Object>> rockets = jsonPath.getList("rocket");
This usage retrieves the rocket object within each element of the JSON array and returns them as a list.

Retrieving More Specific Fields

    List<String> rocketNames = jsonPath.getList("rocket.rocket_name");
This usage retrieves the rocket_name field within the rocket object of each element in the JSON array and returns them as a list.

Summary and Conclusion
When using JSONPath, different methods can be used depending on the structure of the path expression and the data you want to access.
Here are some important points:

Retrieving the Entire JSON Array: 
You can use an empty string ("") or the $ symbol to retrieve the entire JSON array.

Retrieving a Specific Field: 
You can use a specific path expression to access a particular field within the JSON array.

Path Expression Usage: 
JSONPath expressions follow the hierarchy within the JSON data and allow you to access specific keys or fields.

Both methods can be appropriate in certain scenarios, and which one you should use depends on the data you want to access.


    Example JSON and Usage

    [
    {
    "flight_number": 5,
    "mission_name": "RazakSat",
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    },
    {
    "flight_number": 2,
    "mission_name": "John",
    "rocket": {
    "rocket_id": "falcon2",
    "rocket_name": "Falcon 1"
    }
    }
    ]

Explanation:

getList("rocket") retrieves the rocket objects within each element of the JSON array and returns them as a list.
Here, rocket objects are elements of an array, so getList() is used to retrieve each element.

Using getMap()
If your JSON structure directly contains an object, and you want to retrieve this object, you use getMap(). For example:

    {
    "rocket": {
    "rocket_id": "falcon1",
    "rocket_name": "Falcon 1"
    }
    }
In this case, you would use getMap("rocket") to retrieve the rocket object:


        // Access the "rocket" object
        Map<String, Object> rocket = jsonPath.getMap("rocket");
Explanation:

getMap("rocket") retrieves the rocket object directly and returns it as a Map.
Here, the rocket object is a direct JSON object, so getMap() is used.

Summary
getList(): Used to retrieve objects within an array in JSON. It allows you to navigate through the elements of the array.
getMap(): Used to retrieve a JSON object directly. It provides access to the key-value pairs of the JSON object.
Which method you should use depends on the structure of your JSON data and the data you want to retrieve. 
JSONPath expressions allow you to handle these differences effectively.

