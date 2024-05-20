package jsonpath;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.Map;

public class ReceivingJsonObject2 {
    /*
    [
        {
        "id": 2,
        "firstName": "brew",
        "lastName": "more",
        "email": "brew@gmail.com"
        },
        {
        "id": 1,
        "firstName": "john",
        "lastName": "white",
        "email": "john@gmail.com"
        }
    ]
     */
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void gettingObjectUsingGetObjectMethod() {
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getObject("projects", Map.class);
    }
    @Test
    public void gettingObjectUsingGetMapMethod() {
        Map<Object, Object> map = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects");
    }
}
