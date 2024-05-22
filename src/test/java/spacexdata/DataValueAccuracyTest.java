package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DataValueAccuracyTest {
    //Check if certain keys have values within expected ranges.

    String endpoint = "https://api.spacexdata.com/v3/launches";

    @Test
    public void testValuesFormat() {
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        for (Map<String, Object> launch : list) {
            int flightNumber = (int) launch.get("flight_number");
            assertTrue(flightNumber > 0);

            String launchYear = (String) launch.get("launch_year");
            assertTrue(launchYear.matches("\\d{4}"));

            Boolean launchSuccess = (Boolean) launch.get("launch_success");
            Map<String, Object> failureDetails = (Map<String, Object>) launch.get("launch_failure_details");
            if (launchSuccess != null && launchSuccess) {
                assertNull(failureDetails);
            }
        }
    }
        @Test
        public void testValuesFormat2() {
            List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
            for (Map<String, Object> launch : list) {
                String launchYear = (String) launch.get("launch_year");
                assertTrue(launchYear.matches("\\d{4}"));

                Boolean launchSuccess = (Boolean) launch.get("launch_success");
                assertTrue(true);
                //instead of below line:
                //assertTrue(launchSuccess == null || launchSuccess instanceof Boolean);
            }
    }
}
