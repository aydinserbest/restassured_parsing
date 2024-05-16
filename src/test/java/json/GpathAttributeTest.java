package json;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GpathAttributeTest {
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

    /*
    XML tags can have attributes e.g. the id tag in project has a type attribute.
    <id type="integer">1</id>
    We can access the attributes using GPath with the @ symbol:
     */

    @Test
    public void testFindXmlTypeAttribute() {

        // Send an HTTP GET request and get the response
        Response response = RestAssured.
                when().
                get(xmlendpoint).andReturn();

        // Get the response body as a String and print it to the console
        String responseBody = response.getBody().asString();

        // Parse the XML response using the XmlPath
        XmlPath xmlpath = new XmlPath(responseBody);

        // Get the `type` attribute of the `id` element and print it
        String idType = xmlpath.getString("projects.project[0].id.@type");
        System.out.println(idType);

        // Assert that the `type` attribute is equal to "integer"
        assertEquals("integer", idType);
                // or
        assertEquals("integer", xmlpath.get("projects.project[0].id.@type"));
                // or
        //assertEquals("integer", response.then().extract().path("projects.project[0].id.@type"));
    }
}
