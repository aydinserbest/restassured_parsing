package json;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GpathAttributeTest {
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void testGpathAttribute() {
        RestAssured.
                when().
                get(xmlendpoint).
                then().
                assertThat().
                body("projects.project[0].@id", equalTo("1"));
    }
}
