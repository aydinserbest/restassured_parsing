
1- with json response:

        List<Object> list = RestAssured.when().get(jsonendpoint).then().extract().path("projects.project.name");
test passes.

2- with xml response:

        List<Object> path = RestAssured.when().get(xmlendpoint).then().extract().path("projects.project.name");

gives error:java.lang.ClassCastException: class io.restassured.internal.path.xml.NodeChildrenImpl cannot be cast to class java.util.List (io.restassured.internal.path.xml.NodeChildrenImpl is in unnamed module of loader 'app'; java.util.List is in module java.base of loader 'bootstrap')

The reson:


The error you are encountering occurs 
because XML paths in Rest Assured often return NodeChildrenImpl objects rather than List objects, 
which leads to a ClassCastException when you try to cast it directly to a List. 

Unlike JSON paths, which directly map to List objects, XML paths require a different handling due to their hierarchical nature.

To address this, you should first extract the response as a string 
and then use XmlPath to parse and retrieve the list of names properly.

Here's how you can modify your test to work correctly for XML:

Corrected Test Code for XML:



    public class ApiTest {

    @Test
    public void testJsonPath6() {
        String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Get the response body as a String
        String responseBody = response.getBody().asString();

        // Parse the XML response
        List<String> path = XmlPath.from(responseBody).getList("projects.project.name");
        
        System.out.println(path.get(0));
        System.out.println(path.get(1));
    }
    }

Explanation:
Send HTTP GET Request: Retrieve the response from the XML endpoint.

Convert Response Body to String: Extract the response body as a string.

Parse XML Response: Use XmlPath.from() to parse the XML string and extract the list of names.

This approach avoids the ClassCastException by correctly handling the XML path extraction. 
This solution leverages XmlPath.from(responseBody) to handle the XML parsing, 
similar to how JSON paths are handled, ensuring compatibility and correct data extraction.