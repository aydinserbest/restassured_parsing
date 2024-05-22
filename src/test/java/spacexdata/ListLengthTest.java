package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ListLengthTest {
    //Verify that the number of launches retrieved is above a certain threshold.


    String endpoint = "https://api.spacexdata.com/v3/launches";

    @Test
    public void testLaunchListLength() {
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        assertTrue("The launch list should not be empty", list.size() > 0);
        //or
        assertTrue("The launch list should not be empty", true);
    }



}
