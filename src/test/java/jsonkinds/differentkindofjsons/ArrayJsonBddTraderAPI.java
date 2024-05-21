package jsonkinds.differentkindofjsons;

import org.junit.Test;

public class ArrayJsonBddTraderAPI {
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
    /*
    it is a JSON array.
    Each element within the array is a singular JSON object.
    Each object contains fields such as "id," "firstName," "lastName," and "email.
     */
    /*
    Array: This JSON structure starts and ends with square brackets [], indicating it is a JSON array.
    Objects: Each element within the array is enclosed in curly braces {}, signifying they are JSON objects.
    Fields: Each JSON object consists of key-value pairs like "id," "firstName," "lastName," and "email.
     */
    /*
    Using JsonPath:
    Select the appropriate JsonPath method to access specific fields.

    If you need to retrieve a list (array), use the getList() method.
    If you need to retrieve a single object, use the getObject() or getMap() methods.
    If you need to retrieve a string value, use the getString() method.

    Based on this information, you can select the correct method to parse and process the JSON data using JsonPath.
    The getList() method is used to retrieve all elements in an array from the JSON response,
    and you should decide which method to use by examining the JSON structure.
     */
    //BDD-TRADER API from John course:
    String jsonendpoint = "http://localhost:9000/api/clients";
    //Let's explore how we can use JsonPath methods to access specific data within this JSON structure.

    /*
    Retrieving the Entire JSON Structure as a List
    We can use the getList() method to obtain all objects within the array as a list.
     */
    @Test
    public void test1() {

    }
    /*
    Retrieving a List of Specific Fields
    For example, to get a list of all "firstName" fields, we can use the getList() method.
     */
    @Test
    public void test2() {

    }
    /*
    Retrieving a Specific Object
    We can use the getObject() method to get a specific object in the array.
    For example, to get the first user:
     */
    @Test
    public void test3() {

    }
    /*
    Retrieving Field Values
    We can use methods like getString() and getInt() to get the value of a specific field.
    For example, to get the "firstName" field of the first user:
     */
    @Test
    public void test4() {

    }
}
        /*
        Understanding JSON Structure Before Using JSON Path is critical
        when choosing JsonPath methods.
        In this example:

        We identified that the JSON structure is an array.
        We saw that each element in the array is a JSON object.
        Using JsonPath, we can retrieve
                -the entire array,
                -lists of specific fields,
                -specific objects,
                -or specific field values.
         */
        /*
           1- The getList() method is used to retrieve all elements in an array from the JSON response,
            2-Retrieving a List of Specific Fields
            For example, to get a list of all "firstName" fields, we can use the getList() method.
            3-getString() and getInt() to get the value of a specific field
            4-Retrieving a Specific Object
            We can use the getObject() method to get a specific object in the array.
            For example, to get the first user.

             */
