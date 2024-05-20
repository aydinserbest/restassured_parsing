package jsonpath.example1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

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

    // TODO: CCASTING

    @Test
    public void gettingObjectUsingGetMapMethod() {
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
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getMap("projects.project[0]");

        ///
        RestAssured.
                when().
                get(jsonendpoint).
                then().
                extract().body().asString();
        //or
        //.then().extract().path("projects.project[0].name");

        ///
        RestAssured.
                when().
                get(jsonendpoint).body().asString();

    }
    //TODO: asString method?
    @Test
    public void gettingObjectUsingGetMapMethod5() {
        RestAssured.
                when().
                get(jsonendpoint).
                jsonPath().
                getList("projects.project");
        // OR
        String response = RestAssured.
                when().
                get(jsonendpoint).asString();
        JsonPath jsonPath = new JsonPath(response);
        jsonPath.getList("projects.project");

        //or
        JsonPath jsonPath2 = new JsonPath(RestAssured.get(jsonendpoint).asString());
        jsonPath.getList("projects.project");
        //or
        JsonPath jsonPath3 = RestAssured.when().get(jsonendpoint).jsonPath();
        jsonPath3.getList("projects.project");
        //OR
        JsonPath jsonPath4 = new JsonPath(jsonendpoint);
        jsonPath4.getList("projects.project");

        //or
        // Convert the response body to a string
        //String responseBody = response.getBody().asString();

        //ARA LAZIM OLAN METOT, USTTEKILERE GEREK YOKTU:
        Response response2 = RestAssured.
                when().
                get(jsonendpoint).
                andReturn();
        String responseBody = response2.getBody().asString();
        //or
        JsonPath.from(responseBody);
        //OR
        JsonPath jsonPath5 = new JsonPath(response2.body().asString());

        //or path() metodu
        // burda 2 konu var 1-path metodunun kullanisi
        //2- then . extract uygulamasina benzerlik, ustte testte kullanmistik
        String response6 = RestAssured.
                when().
                get(jsonendpoint).then().extract().path("projects.project[0].name");
        System.out.println(response);

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
    //TODO:DENE

    @Test
    public void usingURI() throws URISyntaxException {
        URI uri = URI.create(jsonendpoint);
        Response response = RestAssured.get(uri);
        JsonPath jsonPath = response.jsonPath();
        //OR
        URI uri2 = new URI(jsonendpoint);
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