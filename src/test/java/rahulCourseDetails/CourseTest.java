package rahulCourseDetails;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class CourseTest {
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
    @Test
    public void test() {
            // Load the source file
            File jsonExample = new File(System.getProperty("user.dir"),
                    "src/test/resources/json/rahulcourse.json");

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
}
