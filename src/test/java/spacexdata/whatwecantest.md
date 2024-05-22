### 1

We can perform various tests on the JSON data returned from the SpaceX API. 
These tests ensure that the API is returning the expected results and that the data meets specific criteria. 
The tests can be categorized as follows:

1. JSON Structure Validation
   Verify that the JSON data contains specific keys.
   Validate the types of certain keys.
2. Value Validation
   Check if the launch_year key is in the year format.
   Ensure that the launch_success values are either true or false.
   Test if the flight_number values are in ascending order.
3. Data Consistency
   Verify that the launch_date_utc and launch_date_unix values correspond to each other.
   Check if the data within the rocket object is complete (e.g., rocket_id, rocket_name, rocket_type).
4. Validity of Links
   Check if URLs such as article_link, wikipedia, and video_link are valid.
   Implementation of Tests
   Below are example test codes for each category:

   1. JSON Structure Validation
   

       public class SpaceXApiTests {

       private static final String endpoint = "https://api.spacexdata.com/v3/launches";

       @Test
       public void testJsonStructure() {
           List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        
           for (Map<String, Object> launch : list) {
               assertTrue(launch.containsKey("flight_number"));
               assertTrue(launch.containsKey("mission_name"));
               assertTrue(launch.containsKey("launch_year"));
               assertTrue(launch.containsKey("rocket"));
           }
       }
    }
2. Value Validation
   

    @Test
    public void testValuesFormat() {
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");

    for (Map<String, Object> launch : list) {
        String launchYear = (String) launch.get("launch_year");
        assertTrue(launchYear.matches("\\d{4}"));

        Boolean launchSuccess = (Boolean) launch.get("launch_success");
        assertTrue(launchSuccess == null || launchSuccess instanceof Boolean);
    }
    }
3. Data Consistency
   

       @Test
       public void testDateConsistency() {
       List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    
       for (Map<String, Object> launch : list) {
       String launchDateUtc = (String) launch.get("launch_date_utc");
       Integer launchDateUnix = (Integer) launch.get("launch_date_unix");
    
            if (launchDateUtc != null && launchDateUnix != null) {
                long unixTimeFromUtc = javax.xml.bind.DatatypeConverter.parseDateTime(launchDateUtc).getTimeInMillis() / 1000;
                assertTrue(unixTimeFromUtc == launchDateUnix);
            }
       }
       }
4. Validity of Links
   

       @Test
       public void testValidLinks() {
       List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    
       for (Map<String, Object> launch : list) {
       String articleLink = (String) launch.get("links").get("article_link");
       String wikipediaLink = (String) launch.get("links").get("wikipedia");
       String videoLink = (String) launch.get("links").get("video_link");
    
            if (articleLink != null) {
                assertTrue(articleLink.startsWith("http"));
            }
            if (wikipediaLink != null) {
                assertTrue(wikipediaLink.startsWith("http"));
            }
            if (videoLink != null) {
                assertTrue(videoLink.startsWith("http"));
            }
       }
       }
5. Testing if flight_number Values are in Ascending Order
   

    @Test
    public void testFlightNumberOrder() {
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    
        int previousFlightNumber = -1;
    
        for (Map<String, Object> launch : list) {
            Integer flightNumber = (Integer) launch.get("flight_number");
            assertNotNull(flightNumber);
            assertTrue(flightNumber > previousFlightNumber, "Flight numbers are not in ascending order");
            previousFlightNumber = flightNumber;
        }
    }
Explanation
previousFlightNumber Variable: 
This variable stores the previous flight_number value and is initially set to -1.
Loop: 
Iterates through each launch information in the list.
flightNumber Check: 
Retrieves the flight_number value for each launch and ensures it is not null.
Ascending Order Check: 
Verifies that the current flight_number value is greater than the previous flight_number value. If not, the test fails with the message "Flight numbers are not in ascending order".
Update: 
Updates the previousFlightNumber value with the current flight_number.
This test verifies that the flight_number values are in ascending order. 
If any flight_number value is less than or equal to the previous value, the test fails.

Summary
These tests ensure that the JSON data returned from the SpaceX API is 
validated in terms of structure, values, consistency, and link validity. 
Each test performs a specific category of validation, ensuring that the API is functioning correctly. 
By running these tests, we can be confident that the API returns the expected results.


### 2
When writing tests based on the JSON data, it is appropriate to test the following points:

-Data Structure Accuracy:
Check if the JSON data is in the expected structure.
Ensure that all necessary keys are present for each launch.

-Data Type Accuracy:
Verify that each key in the JSON data is of the correct data type.

-Data Value Accuracy:
Ensure that certain keys have values within expected ranges.

-Logical checks: For example, if launch_success is true, then launch_failure_details should be null.

-List Length:
Verify that the number of launches retrieved is above a certain threshold.

Below, I demonstrate how to write tests for each of these points using Java and RestAssured:

1. Data Structure Accuracy
   Check if all necessary keys are present for each launch.



    public class SpaceXAPITests {

    private final String endpoint = "https://api.spacexdata.com/v3/launches";

    @Test
    public void testLaunchDataStructure() {
        List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
        for (Map<String, Object> launch : list) {
            assertTrue(launch.containsKey("flight_number"));
            assertTrue(launch.containsKey("mission_name"));
            assertTrue(launch.containsKey("launch_year"));
            assertTrue(launch.containsKey("rocket"));
            assertTrue(launch.containsKey("launch_site"));
            // Add similar checks for other necessary keys.
        }
    }
    }
2. Data Type Accuracy
   Check if each key in the JSON data is of the correct data type.


    @Test
    public void testLaunchDataTypes() {
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    for (Map<String, Object> launch : list) {
    assertTrue(launch.get("flight_number") instanceof Integer);
    assertTrue(launch.get("mission_name") instanceof String);
    assertTrue(launch.get("launch_year") instanceof String);
    assertTrue(launch.get("rocket") instanceof Map);
    assertTrue(launch.get("launch_site") instanceof Map);
    // Add similar checks for other keys.
    }
    }
3. Data Value Accuracy
   Check if certain keys have values within expected ranges.


    @Test
    public void testLaunchDataValues() {
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
4. List Length
   Verify that the number of launches retrieved is above a certain threshold.


    @Test
    public void testLaunchListLength() {
    List<Map<String, Object>> list = RestAssured.when().get(endpoint).jsonPath().getList("");
    assertTrue(list.size() > 0, "The launch list should not be empty");
    }
These tests ensure that 
    -the JSON data is structured correctly, 
    -the data types are accurate, 
    -the values are logical, 
    -and the list length meets expectations. 
Each test checks a specific data quality criterion and helps confirm that 
the API returns the expected data in real-world scenarios.