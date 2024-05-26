package trello_API;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TrelloBoardFromConfTest {
    String baseEndPoint = "https://api.trello.com";
    String id = "EGzsl5Jx";
    String apiKey;
    String apiToken;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
        properties.load(fileInputStream);

        apiKey = properties.getProperty("api.key");
        apiToken = properties.getProperty("api.token");
    }
    @Test
    public void getBoardInfoUsingBaseUri() {
        // Setting the base URI for RestAssured
        RestAssured.baseURI = baseEndPoint;
        System.out.println("API Key: " + apiKey); // Check if the API key is being read correctly
        System.out.println("API Token: " + apiToken); // Check if the API token is being read correctly

        String response = RestAssured.
                given().
                pathParam("id", id).
                queryParam("key", apiKey).
                queryParam("token", apiToken).
                when().
                get("/1/boards/{id}/").
                then().
                statusCode(200).
                log().all().
                extract().response().asString();

        System.out.println(response);
    }
}
