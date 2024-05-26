package spacexdata;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void usingFindAll() {
        /*
        this code uses JsonPath to directly fetch all rocket objects that meet the condition (rocket_name == 'Falcon 1').
        This is done using the findAll method within JsonPath, which filters elements based on the specified condition
         */
        String baseEndpoint = "https://api.spacexdata.com/v3/launches";
        String rocketName = "Falcon 1";

        // Fetching the response from the SpaceX API
        JsonPath jsonPath = RestAssured.given()
                .queryParam("rocket_name", rocketName)
                .when()
                .get(baseEndpoint)
                .jsonPath();

        // Directly retrieve all rockets from the launches
        List<Map<String, Object>> rockets = jsonPath.getList("findAll { it.rocket.rocket_name == 'Falcon 1' }.rocket");

        // Assert that the rockets list is not empty
        assertFalse("The rockets list should not be empty.", rockets.isEmpty());

        // Iterate over each rocket and check its properties
        for (Map<String, Object> rocket : rockets) {
            assertEquals("Falcon 1", rocket.get("rocket_name"), "Rocket name should match Falcon 1.");
            System.out.println("Rocket Type: " + rocket.get("rocket_type"));
        }
    }

    @Test
    public void testWithTwoFilters() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;

        List<Map<String, Object>> list = RestAssured.
                given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().
                getList("");

        // Using Stream API to process the list
        list.forEach(launch -> {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket");
            String actualRocketName = (String) rocket.get("rocket_name");
            String actualLaunchYear = (String) launch.get("launch_year");
            assertEquals(rocketName, actualRocketName, "Expected: '" + rocketName + "', but was: '" + actualRocketName + "'");
            assertEquals(String.valueOf(launchYear), actualLaunchYear, "Expected: '" + launchYear + "', but was: '" + actualLaunchYear + "'");
        });
    }
    /*
    [
    {
        "flight_number": 5,
        "mission_name": "RazakSat",
        "mission_id": [],
        "launch_year": "2009",
        "launch_date_unix": 1247456100,
        "launch_date_utc": "2009-07-13T03:35:00.000Z",
        "launch_date_local": "2009-07-13T15:35:00+12:00",
        "is_tentative": false,
        "tentative_max_precision": "hour",
        "tbd": false,
        "launch_window": 0,
        "rocket": {
            "rocket_id": "falcon1",
            "rocket_name": "Falcon 1",
            "rocket_type": "Merlin C",
            "first_stage": {
                "cores": [
                    {
                        "core_serial": "Merlin3C",
                        "flight": 1,
                        "block": null,
                        "gridfins": false,
                        "legs": false,
                        "reused": false,
                        "land_success": null,
                        "landing_intent": false,
                        "landing_type": null,
                        "landing_vehicle": null
                    }
                ]
            },
            "second_stage": {
                "block": 1,
                "payloads": [
                    {
                        "payload_id": "RazakSAT",
                        "norad_id": [
                            35578
                        ],
                        "reused": false,
                        "customers": [
                            "ATSB"
                        ],
                        "nationality": "Malaysia",
                        "manufacturer": "Satrec",
                        "payload_type": "Satellite",
                        "payload_mass_kg": 200,
                        "payload_mass_lbs": 440,
                        "orbit": "LEO",
                        "orbit_params": {
                            "reference_system": "geocentric",
                            "regime": "low-earth",
                            "longitude": null,
                            "semi_major_axis_km": 7048.9,
                            "eccentricity": 0.001701,
                            "periapsis_km": 658.775,
                            "apoapsis_km": 682.755,
                            "inclination_deg": 8.9865,
                            "period_min": 98.161,
                            "lifespan_years": null,
                            "epoch": "2020-12-21T12:05:41.000Z",
                            "mean_motion": 14.66968248,
                            "raan": 133.3568,
                            "arg_of_pericenter": 311.4972,
                            "mean_anomaly": 48.3759
                        }
                    }
                ]
            },
            "fairings": {
                "reused": false,
                "recovery_attempt": false,
                "recovered": false,
                "ship": null
            }
        },
        "ships": [],
        "telemetry": {
            "flight_club": null
        },
        "launch_site": {
            "site_id": "kwajalein_atoll",
            "site_name": "Kwajalein Atoll",
            "site_name_long": "Kwajalein Atoll Omelek Island"
        },
        "launch_success": true,
        "links": {
            "mission_patch": "https://images2.imgbox.com/8d/fc/0qdZMWWx_o.png",
            "mission_patch_small": "https://images2.imgbox.com/a7/ba/NBZSw3Ho_o.png",
            "reddit_campaign": null,
            "reddit_launch": null,
            "reddit_recovery": null,
            "reddit_media": null,
            "presskit": "http://www.spacex.com/press/2012/12/19/spacexs-falcon-1-successfully-delivers-razaksat-satellite-orbit",
            "article_link": "http://www.spacex.com/news/2013/02/12/falcon-1-flight-5",
            "wikipedia": "https://en.wikipedia.org/wiki/RazakSAT",
            "video_link": "https://www.youtube.com/watch?v=yTaIDooc8Og",
            "youtube_id": "yTaIDooc8Og",
            "flickr_images": []
        },
        "details": null,
        "upcoming": false,
        "static_fire_date_utc": null,
        "static_fire_date_unix": null,
        "timeline": {
            "webcast_liftoff": 5
        },
        "crew": null
    }
]
     */

    //In this code, we use the getMap("[0]") method from JsonPath to retrieve the first element of the array.

    @Test
    public void testWithTwoFilters2() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        Map<String, Object> map = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getMap("[0]");
        System.out.println(map.get("launch_year"));
        Map<String, Object> rocket = (Map<String, Object>) map.get("rocket");
        rocket.get("rocket_name");
        System.out.println(rocket.get("rocket_name"));


    }
    @Test
    public void testWithTwoFilters3() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        List<String> rocketNames = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getList("rocket.rocket_name");

        // Print all rocket names to verify
        for (String name : rocketNames) {
            System.out.println(name);
        }

        // Verify the rocket names
        assertEquals("Falcon 1", rocketNames.get(0));
    }
    @Test
    public void testWithTwoFilters4() {
        String rocketName = "Falcon 1";
        int launchYear = 2009;
        List<Map<String,Object>> rocketNames = RestAssured.given().
                queryParam("rocket_name", rocketName).
                queryParam("launch_year", launchYear).
                when().
                get(baseEndpoint).
                jsonPath().getList("rocket");

        // Print all rocket names to verify
        for (Map<String,Object> name : rocketNames) {
            System.out.println(name.get("rocket_name"));
        }

        // Verify the rocket names
       // assertEquals("Falcon 1", rocketNames.get(0));
    }
    @Test
    public void checkifNullOrNot(){
        JsonPath jsonPath = RestAssured.given()
                .queryParam("rocket_name", "Falcon 1")
                .queryParam("launch_year", "2009")
                .when()
                .get("https://api.spacexdata.com/v3/launches")
                .jsonPath();

        Map<String, Object> firstLaunch = jsonPath.getMap("[0]"); // Accessing the first element of the array
        assertNotNull(firstLaunch, "The fetched launch data should not be null.");

        // Assert that the rockets list is not empty
        assertFalse("The rockets list should not be empty.", firstLaunch.isEmpty());

        // Assertions to verify some details in the response
        assertEquals("Falcon 1", ((Map) firstLaunch.get("rocket")).get("rocket_name"), "Rocket name should match.");
        assertEquals(2009, Integer.parseInt(firstLaunch.get("launch_year").toString()), "Launch year should match.");

    }
}
//TODO: getList ve getMap kullanim farki var bu aciklamada
// diger json orneklerini tekrar gozden gecir
// ve 7 sayfalik notu da bu acidan gozden gecir:



