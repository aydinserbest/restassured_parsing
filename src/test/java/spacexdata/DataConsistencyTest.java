package spacexdata;

import io.restassured.RestAssured;
import org.junit.Test;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DataConsistencyTest {

    String endpoint = "https://api.spacexdata.com/v3/launches";


    @Test
    public void testDateConsistency() {

        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");

        for (Map<String, Object> launch : list) {
            String launchDateUtc = (String) launch.get("launch_date_utc");
            Integer launchDateUnix = (Integer) launch.get("launch_date_unix");

            if (launchDateUtc != null && launchDateUnix != null) {
                long unixTimeFromUtc = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(launchDateUtc))
                        .getEpochSecond();
                assertTrue("Unix time from UTC does not match launch_date_unix",
                        unixTimeFromUtc == launchDateUnix);
            }
        }
    }
    /*
    Explanation
DateTimeFormatter.ISO_INSTANT:
This formatter parses the date-time string in the ISO-8601 format used by launch_date_utc.
Instant.from(DateTimeFormatter.ISO_INSTANT.parse(launchDateUtc)):
Parses the launchDateUtc string into an Instant object.
getEpochSecond():
Converts the Instant to Unix time in seconds.
assertTrue:
Compares the Unix time derived from launch_date_utc with launch_date_unix and asserts they are equal.
If they are not equal, it outputs the message "Unix time from UTC does not match launch_date_unix".

     */

}
