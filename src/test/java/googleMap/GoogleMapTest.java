package googleMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

public class GoogleMapTest {
    /*
    {
    "location": {
        "lat": -38.383494,
        "lng": 33.427362
    },
    "accuracy": 50,
    "name": "Frontline house",
    "phone_number": "(+91) 983 893 3937",
    "address": "29, side layout, cohen 09",
    "types": [
        "shoe park",
        "shop"
    ],
    "website": "http://rahulshettyacademy.com",
    "language": "French-IN"
}
     */

    String jsonendpoint = "https://rahulshettyacademy.com/maps/api/place/get/json?place_id=896bddd3cf2306f9a5c88ed071c59585&key=qaclick123";

    @Test
    public void testGoogleMap() {
        Response response = RestAssured.when().get(jsonendpoint);
        System.out.println(response.asString()); // Yanıtı kontrol etmek için

        Map<String, Object> map = response.jsonPath().getMap("location");
        System.out.println(map); // Haritayı kontrol etmek için

        if (map != null) {
            System.out.println(map.get("lat")); // 'lat' anahtarı yanlis yazilinca - null donecek (latitude olmali)
        } else {
            System.out.println("Location map is null");
        }
    }
    @Test
    public void testGoogleMap2() {
        Map<String, Object> map1 = RestAssured.when().get(jsonendpoint).jsonPath().getMap("");
        System.out.println(map1);

        System.out.println(map1.get("location"));
        Map<String, Object> map = RestAssured.when().get(jsonendpoint).jsonPath().getMap("location");
        System.out.println(map);
        System.out.println(map.get("latitude"));

        map.keySet().forEach(System.out::println);
        map.values().forEach(System.out::println);

    }
}
