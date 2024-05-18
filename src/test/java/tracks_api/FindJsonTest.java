package tracks_api;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;

public class FindJsonTest {
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void testFindJson() {
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.find {it.id == 3}.name", equalTo("the new name aniheeiaosono"));
    }
    @Test
    public void testFindAll() {
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.findAll {it.id <= 3}.name.size()", equalTo(2));
    }
    @Test
    public void testFindAll2() {
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.findAll {it.id <= 3}.size()", equalTo(2));
    }
    @Test
    public void testFindXml() {
        RestAssured.
                when().
                get(xmlendpoint).
                then().
                assertThat().
                body("projects.project.find {it.id == 3}.name", equalTo("the new name aniheeiaosono"));
    }
    @Test
    public void testFindXml2() {
        Response response = RestAssured.
                when().
                get(xmlendpoint);

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        XmlPath xmlPath = new XmlPath(responseBody);
        int projectsWithIdLessThanOrEqualTo2 = xmlPath.get("projects.project.findAll {it.id.toInteger() <= 3}.size()");
        System.out.println(projectsWithIdLessThanOrEqualTo2);

        assertEquals(2, projectsWithIdLessThanOrEqualTo2);

        //body("projects.project.find {it.id == 3}.name", equalTo("the new name aniheeiaosono"));
    }
    @Test
    public void testNegativeFind() {
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.findAll {it.id <= 3}.name.size()", not(equalTo(3)));
    }

}
