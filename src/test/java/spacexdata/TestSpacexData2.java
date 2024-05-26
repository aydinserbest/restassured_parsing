package spacexdata;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSpacexData2 {
    String baseEndpoint = "https://api.spacexdata.com/v3/launches";
    /*
    [
    {
        "flight_number": 3,
        "mission_name": "Trailblazer",
        "mission_id": [],
        "launch_year": "2008",
        "launch_date_unix": 1217734440,
        "launch_date_utc": "2008-08-03T03:34:00.000Z",
        "launch_date_local": "2008-08-03T15:34:00+12:00",
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
                        "core_serial": "Merlin1C",
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
                        "payload_id": "Trailblazer",
                        "norad_id": [],
                        "reused": false,
                        "customers": [
                            "NASA"
                        ],
                        "nationality": "United States",
                        "manufacturer": "Space Dev",
                        "payload_type": "Satellite",
                        "payload_mass_kg": null,
                        "payload_mass_lbs": null,
                        "orbit": "LEO",
                        "orbit_params": {
                            "reference_system": "geocentric",
                            "regime": "low-earth",
                            "longitude": null,
                            "semi_major_axis_km": null,
                            "eccentricity": null,
                            "periapsis_km": null,
                            "apoapsis_km": null,
                            "inclination_deg": null,
                            "period_min": null,
                            "lifespan_years": null,
                            "epoch": null,
                            "mean_motion": null,
                            "raan": null,
                            "arg_of_pericenter": null,
                            "mean_anomaly": null
                        }
                    },
                    {
                        "payload_id": "PRESat",
                        "norad_id": [],
                        "reused": false,
                        "customers": [
                            "ORS"
                        ],
                        "nationality": "United States",
                        "manufacturer": null,
                        "payload_type": "Satellite",
                        "payload_mass_kg": null,
                        "payload_mass_lbs": null,
                        "orbit": "LEO",
                        "orbit_params": {
                            "reference_system": "geocentric",
                            "regime": "low-earth",
                            "longitude": null,
                            "semi_major_axis_km": null,
                            "eccentricity": null,
                            "periapsis_km": null,
                            "apoapsis_km": null,
                            "inclination_deg": null,
                            "period_min": null,
                            "lifespan_years": null,
                            "epoch": null,
                            "mean_motion": null,
                            "raan": null,
                            "arg_of_pericenter": null,
                            "mean_anomaly": null
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
        "launch_success": false,
        "launch_failure_details": {
            "time": 140,
            "altitude": 35,
            "reason": "residual stage-1 thrust led to collision between stage 1 and stage 2"
        },
        "links": {
            "mission_patch": "https://images2.imgbox.com/4b/bd/d8UxLh4q_o.png",
            "mission_patch_small": "https://images2.imgbox.com/3d/86/cnu0pan8_o.png",
            "reddit_campaign": null,
            "reddit_launch": null,
            "reddit_recovery": null,
            "reddit_media": null,
            "presskit": null,
            "article_link": "http://www.spacex.com/news/2013/02/11/falcon-1-flight-3-mission-summary",
            "wikipedia": "https://en.wikipedia.org/wiki/Trailblazer_(satellite)",
            "video_link": "https://www.youtube.com/watch?v=v0w9p3U8860",
            "youtube_id": "v0w9p3U8860",
            "flickr_images": []
        },
        "details": "Residual stage 1 thrust led to collision between stage 1 and stage 2",
        "upcoming": false,
        "static_fire_date_utc": null,
        "static_fire_date_unix": null,
        "timeline": {
            "webcast_liftoff": 14
        },
        "crew": null
    },
    {
        "flight_number": 4,
        "mission_name": "RatSat",
        "mission_id": [],
        "launch_year": "2008",
        "launch_date_unix": 1222643700,
        "launch_date_utc": "2008-09-28T23:15:00.000Z",
        "launch_date_local": "2008-09-28T11:15:00+12:00",
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
                        "core_serial": "Merlin2C",
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
                        "payload_id": "RatSat",
                        "norad_id": [
                            33393
                        ],
                        "reused": false,
                        "customers": [
                            "SpaceX"
                        ],
                        "nationality": "United States",
                        "manufacturer": "SpaceX",
                        "payload_type": "Satellite",
                        "payload_mass_kg": 165,
                        "payload_mass_lbs": 363,
                        "orbit": "LEO",
                        "orbit_params": {
                            "reference_system": "geocentric",
                            "regime": "low-earth",
                            "longitude": null,
                            "lifespan_years": null,
                            "epoch": "2020-12-21T02:41:06.000Z",
                            "mean_motion": 14.84904616,
                            "raan": 236.9673,
                            "semi_major_axis_km": 6992.022,
                            "eccentricity": 0.0012404,
                            "periapsis_km": 605.214,
                            "apoapsis_km": 622.56,
                            "inclination_deg": 9.3453,
                            "period_min": 96.975,
                            "arg_of_pericenter": 85.029,
                            "mean_anomaly": 275.1325
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
            "mission_patch": "https://images2.imgbox.com/e0/a7/FNjvKlXW_o.png",
            "mission_patch_small": "https://images2.imgbox.com/e9/c9/T8CfiSYb_o.png",
            "reddit_campaign": null,
            "reddit_launch": null,
            "reddit_recovery": null,
            "reddit_media": null,
            "presskit": null,
            "article_link": "https://en.wikipedia.org/wiki/Ratsat",
            "wikipedia": "https://en.wikipedia.org/wiki/Ratsat",
            "video_link": "https://www.youtube.com/watch?v=dLQ2tZEH6G0",
            "youtube_id": "dLQ2tZEH6G0",
            "flickr_images": []
        },
        "details": "Ratsat was carried to orbit on the first successful orbital launch of any privately funded and developed, liquid-propelled carrier rocket, the SpaceX Falcon 1",
        "upcoming": false,
        "static_fire_date_utc": "2008-09-20T00:00:00.000Z",
        "static_fire_date_unix": 1221868800,
        "timeline": {
            "webcast_liftoff": 5
        },
        "crew": null
    }
]
     */

    /*
    regarding the two methods for handling the JSON response from the SpaceX API:

    1. Retrieving All Launch Entries and Accessing the Rocket Object
    This method involves retrieving a list of all launch entries from the API
    and accessing the rocket object within each launch.
    This is useful when you want to inspect the entire response and process rocket information along with other data.

     */
    //Gets all launch data as a list of maps, where each map represents a single launch entry.

    @Test
    public void testFetchAllLaunchesAndAccessRocket() {
        // Fetching the response from the SpaceX API
        JsonPath jsonPath = RestAssured.given()
                .when()
                .get(baseEndpoint)
                .jsonPath();

        // Retrieve all launches as a list of maps
        List<Map<String, Object>> launches = jsonPath.getList("");

        // Assert that the list of launches is not empty
        assertFalse("The list of launches should not be empty.", launches.isEmpty());

        // Iterate over each launch and access the rocket information
        for (Map<String, Object> launch : launches) {
            Map<String, Object> rocket = (Map<String, Object>) launch.get("rocket"); // Access the rocket object from each launch

            // Assert that the rocket object is not null
            assertNotNull(rocket, "Rocket information should not be null.");

            // Example: Print or check the rocket_name to ensure data integrity
            System.out.println("Rocket Name: " + rocket.get("rocket_name"));
            // Additional operations related to the rocket can be performed here
        }
    }

    /*
    2. Directly Retrieving 'Rocket' Objects Using JsonPath
    This method uses JsonPath queries to directly fetch all rocket objects as a list.
    This is more efficient if you are only focused on rocket information
    and do not need other details about the launches.
    When you need to quickly pull and process information related only to rocket, without requiring other launch details.


     */
    @Test
    public void testDirectAccessToRocketData() {
        // Define the API endpoint
        String baseEndpoint = "https://api.spacexdata.com/v3/launches";

        // Fetching the response from the SpaceX API
        JsonPath jsonPath = RestAssured.given()
                .when()
                .get(baseEndpoint)
                .jsonPath();

        // Directly retrieve all rockets from the launches
        List<Map<String, Object>> rockets = jsonPath.getList("findAll { it.rocket }.rocket");

        // Assert that the list of rockets is not empty
        assertFalse("The list of rockets should not be empty.", rockets.isEmpty());

        // Iterate over each rocket and check its properties
        for (Map<String, Object> rocket : rockets) {
            // Ensure the rocket name is as expected (assuming a known set of rockets)
            assertEquals("Falcon 1", rocket.get("rocket_name"), "Rocket name should match Falcon 1.");
            System.out.println("Rocket Type: " + rocket.get("rocket_type"));
        }
    }
    /*
     the explanation for "findAll { it.rocket }.rocket":

The expression "findAll { it.rocket }.rocket" is a Groovy-based JsonPath filter.
It uses the findAll method of JsonPath to filter elements that meet a specific condition.
In this expression, each item in the root array is represented by it, and the { it.rocket } condition selects elements
that contain a rocket object. This condition effectively filters each launch (launch) entry that has a rocket object.
The .rocket subsequently accesses the rocket property within these filtered elements,
thus the JsonPath query ultimately retrieves a list of rocket objects only.

This usage is ideal for extracting only the rocket information from all the launch data
and compiling these rocket objects into a separate list.
This method streamlines accessing specific nested information within a complex JSON structure,
particularly when only a subset of the entire data is required for further processing or analysis.
     */
    @Test
    public void testDirectAccessToRocketData2() {

        String rocketName = "Falcon 1";

        // Fetching the response from the SpaceX API
        List<Map<String, Object>> rockets = RestAssured.given()
                .queryParam("rocket_name", rocketName)
                .when()
                .get(baseEndpoint)
                .jsonPath().getList("rocket");
        for (Map<String, Object> rocket : rockets) {
            System.out.println(rocket.get("rocket_name"));
        }

    }
}