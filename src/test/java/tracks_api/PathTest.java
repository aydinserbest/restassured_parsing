package tracks_api;

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
    public void testJsonPath8() {
        List<Object> list = RestAssured.when().get(jsonendpoint).jsonPath().getList("projects.project.name");
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
            //or
            String string2 = response.asString();
                //or
                String string3 = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(string);
        System.out.println(jsonPath.getString("projects.project[0].name"));
        System.out.println(jsonPath.getString("projects.project.name"));
        System.out.println(jsonPath.getList("projects.project.name"));

        JsonPath js = new JsonPath(string2);

        System.out.println(js.getString("projects.project.name"));
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
        /*
        XML paths in Rest Assured often return NodeChildrenImpl objects rather than List objects,
        which leads to a ClassCastException when you try to cast it directly to a List.

        Unlike JSON paths, which directly map to List objects, XML paths require a different handling due to their hierarchical nature.

        To address this, you should first extract the response as a string
        and then use XmlPath to parse and retrieve the list of names properly.
         */
        //so the below line will give error
       // List<Object> path = RestAssured.when().get(xmlendpoint).then().extract().path("projects.project.name");
        //to fix it:
        String path = RestAssured.when().get(xmlendpoint).asString();
                XmlPath xmlPath = new XmlPath(path);
        List<Object> path2 = xmlPath.getList("projects.project.name");

        System.out.println(path2.get(0));
        System.out.println(path2.get(1));
    }
}
