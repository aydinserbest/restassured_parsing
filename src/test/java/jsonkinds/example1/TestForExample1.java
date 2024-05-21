package jsonkinds.example1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestForExample1 {
    /*
    {
        "projects": {
            "project": [
                {
                    "id": 1,
                    "name": "A New Project"
                },
                {
                    "id": 3,
                    "name": "the new name"
                }
                        ]
                    }
    }
     */
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    /*
    TODO: BUNLARI KULLAN TESTLERDE
    // Send an HTTP GET request, get the response, and convert the response body to a string
    // Parse the JSON response into a Project array
    // Access the first project
    // Use the JsonPath library to parse the response
    // Access the name field of the first project in the JSON response
    // Access the name field of the first project in the JSON response
*/
    @Test
    public void gettingObjectUsingGetObjectMethod() {
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getObject("projects", Map.class);
    }
    @Test
    public void gettingObjectUsingGetMapMethod() {
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects");
    }

    // TODO: CCASTING

    @Test
    public void gettingObjectUsingGetMapMethod20() {
        Map<String, Object> projects = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects");
        List<Map<String, Object>> projectList = (List<Map<String, Object>>) projects.get("project");

        System.out.println(projectList.get(0).get("id"));
        System.out.println(projectList.get(0).get("name"));
    }
    //TODO: WITHOUT CCASTING
    @Test
    public void gettingObjectUsingGetMapMethod2() {
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getList("projects.project");
    }
    @Test
    public void gettingObjectUsingGetMapMethod3() {
        Map<Object, Object> map = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects.project[0]");
        System.out.println(map.get("name"));
    }

    //TODO: 3 ways
    @Test
    public void gettingObjectUsingGetMapMethod4() {
        Map<String, Object> map = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects.project[0]");

        // Print the "name" value
        System.out.println(map.get("name"));

        // Verify the "name" value
        assertEquals("A New Projectaniheeiadtatd", map.get("name"));
    }
    @Test
    public void gettingObjectUsingExtractBodyMethod() {

        String response = RestAssured.
                when().
                get(jsonendpoint).
                then().
                extract().body().asString();

        JsonPath jsonPath = new JsonPath(response);
        String nameOfFirstElement = jsonPath.get("projects.project[0].name");

        //verify the 'name' value
        assertEquals("A New Projectaniheeiadtatd", nameOfFirstElement);
    }
    @Test
    public void extractPathMethod(){
        String path = RestAssured.
                when().
                get(jsonendpoint).
                then().
                extract().path("projects.project[0].name");

        //verify the 'name' value
        assertEquals("A New Projectaniheeiadtatd", path);
    }
    @Test
    public void gettingObjectAsString() {
        String response = RestAssured.
                when().
                get(jsonendpoint).asString();
        JsonPath jsonPath = new JsonPath(response);
        jsonPath.getList("projects.project");
    }
    @Test
    public void returnStringInsideParameter() {
        JsonPath jsonPath = new JsonPath(RestAssured.get(jsonendpoint).asString());
        jsonPath.getList("projects.project");
    }
    @Test
    public void returnJsonPath() {
        JsonPath jsonPath = RestAssured.when().get(jsonendpoint).jsonPath();
        jsonPath.getList("projects.project");
    }
    @Test
    public void andReturnMethod() {
        Response response = RestAssured.
                when().
                get(jsonendpoint).
                andReturn();
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        String nameOfFirstElement = jsonPath.get("projects.project[0].name");
        //verify the 'name' value
        assertEquals("A New Projectaniheeiadtatd", nameOfFirstElement);
    }
    @Test
    public void fromMethod() {
        String responseBody = RestAssured.
                when().
                get(jsonendpoint).asString();

        JsonPath from = JsonPath.from(responseBody);
    }
    @Test
    public void fromMethod2() {
        Response response = RestAssured.
                when().
                get(jsonendpoint).
                andReturn();

        JsonPath jsonPath5 = new JsonPath(response.body().asString());
    }
    @Test
    public void fromMethod3() throws MalformedURLException {
        // Define the URL to the JSON resource
        URL jsonURL = new URL("https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php");

        // Create a JsonPath object using the URL
        JsonPath jsonPath = new JsonPath(jsonURL);

        // Now you can use jsonPath to access JSON data
        System.out.println(jsonPath.getString("projects.project[0].name"));
        new JsonPath()
    }


    //TODO:
    /*
     // Parse the JSON response into a List<Project>
        List<Project> projects = response.jsonPath().getObject("projects.project", new TypeRef<List<Project>>() {});

     */
    //TODO:
    /*
    List<Object> list = RestAssured.when().get(jsonendpoint).then().extract().path("projects.project.name");
            System.out.println(list.get(0));
     */
    //TODO:
    /*

    // Parse the JSON response into a Project array
        Project[] projects = response.jsonPath().getObject("projects.project", Project[].class);

        // Access the first project
        Project firstProject = projects[0];
     */
    //TODO: bunu List<> olarak da dene
    //Project[] projects = response.jsonPath().getObject("projects.project", Project[].class);

    //TODO:alttaki kod calisiyor ama
    // ebookta -parsing from uri basligindaki konu, chatgpt destekli aciklama yaz

    @Test
    public void usingURI() throws URISyntaxException {
        URI uri = URI.create(jsonendpoint);
        Response response = RestAssured.get(uri);
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("projects.project[0].name"));
        //OR
        //URI uri2 = new URI(jsonendpoint);
        //uri2.getObject("projects.project[0].name");
    }
}
/*
TODO: new JsonPath()  neleri aliyor parametre bak, foto cek, file dan okumada var
dene, solda jsonfile var response altinda olur mu, bir de rahulun frameworkte mi ne vardi ona da bak

 */
/*
    TODO: BUNU DA KULLAN EXAMPLE OLARAK BIR TESTTE:

    I can then use the JsonPath object to get the fields and values using GPath format queries,
    the following returns a List of HashMap objects:
    List<HashMap<String,String>> ret = jsonPath.get("projects.project");
        Assert.assertEquals(6, ret.size());
 I can use the HashMap to access the field values.
  Assert.assertEquals("A New Projectaniheeiadtatd",
                            ret.get(0).get("name"));
 Only the JsonPath returns a HashMap. A related method on XmlPath is getList which returns a List of Strings:
 XmlPath xmlPath = new XmlPath(response.body().asString());

        List ret = xmlPath.getList("projects.project");
        Assert.assertEquals(6, ret.size());


 */
/*
TODO:deserizilation ornegi yaparken
ebook frameworkunde ProjectFromXmlOrJson ve ProjectJson ile bu ikisinin testi olan
RestAssuredJSONExamplesTest ye bak
biri jsonfile icin digeri xml file icin

ProjectJson projectFromJson = RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getObject("projects.project[1]", ProjectJson.class);

        Assert.assertEquals(3, projectFromJson.id);



        ProjectFromXmlOrJson aProjectFromJson =
                jsonPath.getObject("projects.project[1]",
                                    ProjectFromXmlOrJson.class);
        Assert.assertEquals(3, aProjectFromJson.id);
                                    yapmis iyi ornek

 */

/*
TODO: ebook frameworkten , dene, RestAssuredXMLExamplesTest testinde var
URI DAN -DESIRIZILIATION ILE

 @Test
    public void anXmlDeserializationExampleFromUrl() throws URISyntaxException {

        URI endpoint = new URI(xmlendpoint);

        ProjectFromXmlOrJson projectFromXml;
        projectFromXml = new XmlPath(endpoint).
                                getObject("projects.project[1]",
                                          ProjectFromXmlOrJson.class);

        Assert.assertEquals(3, projectFromXml.id);
    }
    URI objesine mesela jsonendpointi ver, bu objeyi de
    POJO CLASSINA  VER

 */
/*
TODO:
@Test
    public void simpleJSONRestAssuredExample(){

        RestAssured.
            when().
                get(jsonendpoint).
            then().assertThat().
                body("projects.project[0].name",
                     equalTo("A New Projectaniheeiadtatd"));
    }

 */