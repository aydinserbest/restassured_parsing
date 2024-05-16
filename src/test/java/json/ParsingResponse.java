package json;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsingResponse {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";
    /*
    I would normally convert the response into an Object, or parse it with JsonPath, XmlPath, or GSon.
    JsonPath is a REST Assured supplied library for parsing JSON
    XmlPath is a REST Assured library for parsing XML
    GSon is a Google library for parsing JSON
     */

    /*
    I would convert the body of the Response to a String and parse it into a JsonPath object.
    JsonPath jsonPath = new JsonPath(response.body().asString());
            or parse it into a XmlPath object.
    XmlPath xmlPath = new XmlPath(response.body().asString());
     */
    /*
    I can then use the JsonPath object to get the fields and values using GPath format queries,
     */
    //Only the JsonPath returns a HashMap.
    // A related method on XmlPath is getList which returns a List of Strings:
    //
    @Test
    public void jsonResponseHashMap() {
        Response response = RestAssured.
                when().
                get(jsonendpoint);

        JsonPath jsonPath = new JsonPath(response.body().asString());

        List<HashMap<String, String>> ret = jsonPath.get("projects.project");

        assertEquals(6, ret.size());
        assertEquals("A New Projectaniheeiadtatd", ret.get(0).get("name"));

    }
    @Test
    public void xmlResponseList() {
        Response response = RestAssured.
                when().
                get(xmlendpoint);

        XmlPath xmlPath = new XmlPath(response.body().asString());

        List ret = xmlPath.getList("projects.project");

        assertEquals(6, ret.size());
    }
}
