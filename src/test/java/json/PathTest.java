package json;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

public class PathTest {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

    @Test
    public void testJsonPath7() {
        List<Object> list = RestAssured.when().get(jsonendpoint).then().extract().path("projects.project.name");
        System.out.println(list.get(0));
    }
    @Test
    public void testJsonPath() {
        List<Object> list = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().getList("projects.project.name");
        System.out.println(list.get(0));
    }
    @Test
    public void testJsonPath2() {
        Response response = RestAssured.
                when().
                get(jsonendpoint);
        String string = response.body().asString();

        JsonPath jsonPath = new JsonPath(string);
        System.out.println(jsonPath.getString("projects.project[0].name"));
        System.out.println(jsonPath.getString("projects.project.name"));
    }
    @Test
    public void testJsonPath3() {
        Response response = RestAssured.
                when().
                get(jsonendpoint);
        String string = response.body().asString();

        JsonPath jsonPath = new JsonPath(string);
        List<Object> list = jsonPath.getList("projects.project.name");
        System.out.println(list.get(0));
        System.out.println(jsonPath.getString("projects.project[0].name"));
        System.out.println(jsonPath.getString("projects.project.name"));
    }
    @Test
    public void testJsonPath4() {
        Response response = RestAssured.
                when().
                get(xmlendpoint);
        String string = response.body().asString();

        XmlPath xmlPath = new XmlPath(string);
        List<Object> list = xmlPath.getList("projects.project.name");
        System.out.println(list.get(0));
        System.out.println(xmlPath.getString("projects.project[0].name"));
        System.out.println(xmlPath.getString("projects.project.name"));
    }
    @Test
    public void testJsonPath5() {
        List<Object> list = RestAssured.when().get(xmlendpoint).xmlPath().getList("projects.project.name");

        System.out.println(list.get(0));
        System.out.println(list.get(1));

    }
    @Test
    public void testJsonPath6() {
        List<Object> path = RestAssured.when().get(xmlendpoint).then().extract().path("projects.project.name");

        System.out.println(path.get(0));
        System.out.println(path.get(1));

    }
}
