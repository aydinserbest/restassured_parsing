package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FlightNumbersTest {
    //Testing if flight_number Values are in Ascending Order

    String endpoint = "https://api.spacexdata.com/v3/launches";


    @Test
    public void testFlightNumberOrder() {
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");

        int previousFlightNumber = -1;

        for (Map<String, Object> launch : list) {
            Integer flightNumber = (Integer) launch.get("flight_number");
            assertNotNull(flightNumber);
            assertTrue("Flight numbers are not in ascending order", flightNumber > previousFlightNumber);
            previousFlightNumber = flightNumber;
        }
    }
}
