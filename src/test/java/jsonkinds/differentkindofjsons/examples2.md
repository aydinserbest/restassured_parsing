
    JSON Structures and Key-Value Relationships
### 1
JSON 1: Flat Structure

        {
    "id": 1,
    "name": "A New Project"
    }
In this JSON structure:

"id": Key, 1: Value
"name": Key, "A New Project": Value

### 2
JSON 2: Nested Structure

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
In this JSON structure:

"projects": Key, the value is an object.
"project": Key, the value is an array.
First element in the array:
"id": Key, 1: Value
"name": Key, "A New Project": Value
Second element in the array:
"id": Key, 3: Value
"name": Key, "the new name": Value

Terminology
Key: The name of a field in a JSON object. For example, "id", "name", "projects", "project".
Value: The data corresponding to a key. Values can be of various types: number (1, 3), string ("A New Project"), object ({"project": [...]}), or array ([...]).
Object: A collection of key-value pairs. For example, {"id": 1, "name": "A New Project"} or {"project": [...]}.
Array: An ordered list of values. For example, [{...}, {...}].
Expression for JSON 2
The "projects" key: Its value is an object.
This object contains a key named "project".
The "project" key: Its value is an array.
The array contains objects.
Each object in the array has keys like "id" and "name", with their respective values.

### 3
JSON 3: Array of Objects

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

In this JSON structure:

The entire structure is an array containing objects.
First object in the array:
"id": Key, 2: Value
"firstName": Key, "brew": Value
"lastName": Key, "more": Value
"email": Key, "brew@gmail.com": Value
Second object in the array:
"id": Key, 1: Value
"firstName": Key, "john": Value
"lastName": Key, "white": Value
"email": Key, "john@gmail.com": Value
Terminology
Key: The name of a field in a JSON object. For example, "id", "firstName", "lastName", "email".
Value: The data corresponding to a key. Values can be of various types: number (1, 2), string ("brew", "john"), or object ({"id": 2, "firstName": "brew", "lastName": "more", "email": "brew@gmail.com"}).
Object: A collection of key-value pairs. For example, {"id": 2, "firstName": "brew", "lastName": "more", "email": "brew@gmail.com"}.
Array: An ordered list of values. For example, [{"id": 2, "firstName": "brew", "lastName": "more", "email": "brew@gmail.com"}, {"id": 1, "firstName": "john", "lastName": "white", "email": "john@gmail.com"}].
Expression for JSON 3
The entire structure is an array containing objects.
Each object in the array has keys like "id", "firstName", "lastName", and "email", with their respective values.
This terminology clarifies the structure and relationships within the flat JSON structure, the nested JSON structure, and the array of objects JSON structure.


