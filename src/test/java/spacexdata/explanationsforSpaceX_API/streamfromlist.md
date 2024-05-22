### 1

The following line is a Java Stream expression used to extract the flight_number values 
from the JSON data obtained from the SpaceX API. 
Let's break down this expression step by step:

    list.stream().map(map -> map.get("flight_number")).forEach(System.out::println);

Step-by-Step Explanation
list.stream():

Initiates a Stream on the collection named list.
Stream is an API introduced in Java 8 that allows functional programming on collections.
In this example, list is of type List<Map<String, Object>>, and each Map represents a JSON object.

.map(map -> map.get("flight_number")):

Defines a transformation to be applied to each element (each map) in the Stream.
Here, map represents an element of type Map<String, Object>, which corresponds to each JSON object.
map.get("flight_number") retrieves the value associated with the key "flight_number" from the Map.
The map function extracts the flight_number value from each JSON object, creating a new Stream of these values.

.forEach(System.out::println):

Applies an operation to each element in the Stream (in this case, the flight_number values).
System.out::println is a Consumer function that prints each flight_number value to the console.
forEach applies the specified operation (System.out::println) to each element in the Stream.

Overall Flow
First, a Stream is initiated on each Map in the list.
The flight_number value is extracted from each Map element.
The extracted flight_number values are printed to the console one by one using forEach.
This way, the flight_number values for each flight in the JSON data retrieved from the SpaceX API 
are printed to the console.

Example JSON and Output
Based on the JSON example you provided, when this code is executed, 
the following values will be printed to the console:

1
2
These values correspond to the flight_number values of the flights in the JSON data.


### 2

here is the detailed explanation of the Java code using RestAssured to fetch data from the SpaceX API 
and process it with a stream expression:

Code Explanation in English
Let's go through the Java code step by step to understand how it uses RestAssured 
to fetch data from the SpaceX API and process it using a stream expression:

    String endpoint = "https://api.spacexdata.com/v3/launches";
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    list.stream().map(map -> map.get("flight_number")).forEach(System.out::println);
Endpoint Definition:

    String endpoint = "https://api.spacexdata.com/v3/launches";
Here, a variable named endpoint is defined and assigned the URL of the SpaceX API endpoint. 
This endpoint returns JSON data containing all SpaceX launch information.

Fetching Data from the API and Converting to a List:
    
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");

The expression RestAssured.when().get(endpoint) sends a GET request to the specified endpoint URL.
The method jsonPath().getList("") converts the JSON response from the API into a List object. 
The getList("") method is used here to directly convert the JSON response into a list.
This list stores each JSON object as a Map<String, Object>. 
Each launch information is represented as a map containing key-value pairs from the JSON data.

Processing and Printing Data with a Stream:

    list.stream().map(map -> map.get("flight_number")).forEach(System.out::println);

The expression list.stream() creates a stream from the list. 
The Java Stream API provides a powerful and functional way to process data.
The map(map -> map.get("flight_number")) expression takes each map object in the stream (each JSON object) 
and extracts the value associated with the flight_number key. 
This is a transformation operation within the stream. Here, map is a transformation function, 
and map.get("flight_number") retrieves the flight_number value for each launch.
The forEach(System.out::println) expression performs the specified action (in this case, printing) for each element 
in the stream. System.out::println prints each flight_number value to the console.
Summary
In summary, this code snippet fetches all launch information from the SpaceX API, 
extracts the flight_number value for each launch, and prints these values to the console.