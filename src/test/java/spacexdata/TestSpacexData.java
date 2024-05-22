package spacexdata;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: this test uses junit 5,
// because with junit 4, assertEquals() is not working unless we add trim().replaceAll("\\s+", " ")
// with junit 4 , assertTrue() is  working
// assertThat() method is working from assertj.core.
// but assertEquals() is not working with junit 4

public class TestSpacexData {
    String baseEndpoint = "https://api.spacexdata.com/v3/launches";


    @Test
    public void filteredResults() {
        String expectedRocketName = "Falcon 1";
        List<Map<String, Object>> list = RestAssured.given().queryParam("rocket_name", expectedRocketName)
                .when().get(baseEndpoint).jsonPath().getList("");

        // Using Stream API to process the list
        list.stream().forEach(launch -> {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
            String actualRocketName = (String) rocket.get("rocket_name");
            assertEquals(expectedRocketName, actualRocketName, "Expected: '" + expectedRocketName + "', but was: '" + actualRocketName + "'");


       // assertTrue("Expected: '" + expectedRocketName + "', but was: '" + actualRocketName + "'", expectedRocketName.equals(actualRocketName));
        // Cleaning up the rocket name
      //String cleanedActualName = actualRocketName.trim().replaceAll("\\s+", " ");

        //assertEquals(expectedRocketName, cleanedActualName, "Expected: '" + expectedRocketName + "', but was: '" + cleanedActualName + "'");

        });
    }

}


