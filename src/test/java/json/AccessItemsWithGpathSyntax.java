package json;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class AccessItemsWithGpathSyntax {
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
}
