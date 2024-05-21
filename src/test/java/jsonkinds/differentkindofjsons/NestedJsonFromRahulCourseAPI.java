package jsonkinds.differentkindofjsons;

import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class NestedJsonFromRahulCourseAPI {
    /*
    {
  "instructor": "RahulShetty",
  "url": "rahulshettycademy.com",
  "services": "projectSupport",
  "expertise": "Automation",
  "courses": {
    "webAutomation": [
      {
        "courseTitle": "Selenium Webdriver Java",
        "price": "50"
      },
      {
        "courseTitle": "Cypress",
        "price": "40"
      },
      {
        "courseTitle": "Protractor",
        "price": "40"
      }
    ],
    "api": [
      {
        "courseTitle": "Rest Assured Automation using Java",
        "price": "50"
      },
      {
        "courseTitle": "SoapUI Webservices testing",
        "price": "40"
      }
    ],
    "mobile": [
      {
        "courseTitle": "Appium-Mobile Automation using Java",
        "price": "50"
      }
    ]
  },
  "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
}
     */
    File jsonExample;
    @Before
    public void setUp() {
        // Load the source file
        jsonExample = new File(System.getProperty("user.dir"),
                "src/test/resources/json/rahulcourse.json");
    }
    @Test
    public void test() {
            // Convert the JSON content into a JsonPath object
            JsonPath jsonPath = new JsonPath(jsonExample);
        Map<String, Object> map = jsonPath.getMap("");
        map.get("instructor");
        map.get("url");
        map.get("services");
        map.get("expertise");

        System.out.println(jsonPath.getList("courses.webAutomation."));
        System.out.println(jsonPath.getString("courses.api[0].courseTitle"));
        System.out.println(jsonPath.getList("courses.api.courseTitle"));
        jsonPath.getList("courses.mobile");

        map.get("courses");

        System.out.println(jsonPath.getMap(""));
        System.out.println(jsonPath.getString("instructor"));
    }
    @Test
    public void accessKeys(){
        // Convert the JSON content into a JsonPath object
        JsonPath jsonPath = new JsonPath(jsonExample);

        // Get the map at the root of the JSON
        Map<String, Object> jsonMap = jsonPath.getMap("");

        if (jsonMap != null) {
            // Print the keys in the map
            Set<String> keys = jsonMap.keySet();
            System.out.println("Keys in the map:");
            for (String key : keys) {
                System.out.println(key);
            }

            // Example to show how to access a specific key, here just an example
            System.out.println("Instructor: " + jsonMap.get("instructor"));
        } else {
            System.out.println("jsonMap is null");
        }
    }
    /*
    Explanations:
    1-To print the keys of a Map,
    you can use the keySet() method.
    The keySet() method returns all the keys in the Map as a Set.
    You can print these keys by following these steps:

        -Retrieve the keys of the Map using the keySet() method.
        -Print these keys.

    2-Accessing the Root Map:
    The code now accesses the root of the JSON directly by calling jsonPath.getMap("").
    This will provide the top-level keys in your JSON.

    When you run the test, the output should list all the top-level keys in the JSON
    (instructor, url, services, expertise, courses, linkedIn). For example:

    Keys in the map:
    instructor
    url
    services
    expertise
    courses
    linkedIn
    Instructor: RahulShetty

This output confirms that your JSON is being read correctly and the keys are being accessed properly.
If you need to access nested keys, you would adjust the path accordingly.




     */
}
