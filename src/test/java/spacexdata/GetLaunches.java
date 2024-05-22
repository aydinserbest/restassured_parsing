package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GetLaunches {
    /*
    The expression RestAssured.when().get(endpoint) sends a GET request to the specified endpoint URL.
    The method jsonPath().getList("") converts the JSON response from the API into a List object.
    The getList("") method is used here to directly convert the JSON response into a list.
    This list stores each JSON object as a Map<String, Object>.
    Each launch information is represented as a map containing key-value pairs from the JSON data.
     */
    @Test
    public void getLaunches(){
        String endpoint = "https://api.spacexdata.com/v3/launches";
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        list.stream().map(map -> map.get("flight_number")).forEach(System.out::println);

        List<Integer> list1 = RestAssured.when().get(endpoint).jsonPath().getList("flight_number");
        System.out.println(list1);

    }
}
/*
String endpoint = "https://api.spacexdata.com/v3/launches";
Here, a variable named endpoint is defined and assigned the URL of the SpaceX API endpoint.
This endpoint returns JSON data containing all SpaceX launch information.

Processing and Printing Data with a Stream:

list.stream().map(map -> map.get("flight_number")).forEach(System.out::println);
The expression list.stream() creates a stream from the list.

The map(map -> map.get("flight_number")) expression takes each map object in the stream (each JSON object)
and extracts the value associated with the flight_number key.
map.get("flight_number") retrieves the flight_number value for each launch.
The forEach(System.out::println) expression performs the specified action (in this case, printing) for each element in the stream.
System.out::println prints each flight_number value to the console.
Summary
In summary, this code snippet fetches all launch information from the SpaceX API,
extracts the flight_number value for each launch,
and prints these values to the console.
 */
