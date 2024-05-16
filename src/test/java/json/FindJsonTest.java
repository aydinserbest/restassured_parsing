package json;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class FindJsonTest {
    @Test
    public void testFindJson() {
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.find {it.id == 3}.name", equalTo("the new name aniheeiaosono"));
    }
    @Test
    public void testFindJson2() {
        String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
        RestAssured.
                when().get(jsonendpoint).
                then().
                assertThat().
                body("projects.project.findAll {it.id <= 3}.name.size()", equalTo(2));
    }
}
