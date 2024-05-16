package json;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class ParsingJson {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

    //REST Assured uses GPath syntax to access items in the body:
    //"projects.project[0].name"

    @Test
    public void testJsonEndpoint() {
        RestAssured.
                when().
                get(jsonendpoint).
                then().
                assertThat().
                body("projects.project[0].name", equalTo("A New Projectaniheeiadtatd"));

    }
    @Test
    public void testJsonArraySize() {
        RestAssured.
                when().
                get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.size()", equalTo(6));

    }
    //multiple assertions with and()
    @Test
    public void testJsonChainCalls() {
        RestAssured.
                when().
                get(jsonendpoint).
                then().
                assertThat().
                body("projects.project[0].name", equalTo("A New Projectaniheeiadtatd")).
                and().
                body("projects.project[1].name", equalTo("the new name aniheeiaosono"));

    }
    @Test
    public void testXmlEndpoint(){
        RestAssured.
                when().
                get(xmlendpoint).
                then().
                body("projects.project[0].name", equalTo("A New Projectaniheeiadtatd"));

    }
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
