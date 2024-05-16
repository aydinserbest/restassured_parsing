package json;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorkingWithGetNodeChildrenMethod {
    String jsonendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";

    //the author:that I prefer to work with the getNodeChildren method, rather than getList when working with XmlPath.

    //Using getNodeChildren with NodeChildren:

    @Test
    public void testXmlWithGetNodeChildren1() {
        String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";  // Replace with the actual endpoint

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Get the response body as a String
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        // Parse the XML response with DOCTYPE declaration allowed
        XmlPath xmlPath = new XmlPath(responseBody).using(new XmlPathConfig().allowDocTypeDeclaration(true));

        // Access all child elements of the projects
        NodeChildren nodes = xmlPath.getNodeChildren("projects.project");

        // Check the number of projects
        assertEquals(6, nodes.size());  // Expecting 6 projects

        // Loop through each node and check the name and id
        for (int i = 0; i < nodes.size(); i++) {
            Node node = (Node) nodes.get(i);
            String name = node.get("name").toString();
            String id = node.get("id").toString();
            System.out.println("Project Name: " + name + ", Project ID: " + id);
        }

        // Check the name and id of each project
        assertEquals("A New Projectaniheeiadtatd", ((Node) nodes.get(0)).get("name").toString());
        assertEquals("1", ((Node) nodes.get(0)).get("id").toString());

        assertEquals("the new name aniheeiaosono", ((Node) nodes.get(1)).get("name").toString());
        assertEquals("3", ((Node) nodes.get(1)).get("id").toString());

        assertEquals("A New Projectaniheeiaoaees", ((Node) nodes.get(2)).get("name").toString());
        assertEquals("5", ((Node) nodes.get(2)).get("id").toString());

        assertEquals("A New Projectaniheeidrdhtd", ((Node) nodes.get(3)).get("name").toString());
        assertEquals("6", ((Node) nodes.get(3)).get("id").toString());

        assertEquals("the new name aniheeidrettt", ((Node) nodes.get(4)).get("name").toString());
        assertEquals("8", ((Node) nodes.get(4)).get("id").toString());

        assertEquals("A New Projectaniheeidrrhad", ((Node) nodes.get(5)).get("name").toString());
        assertEquals("10", ((Node) nodes.get(5)).get("id").toString());
    }
    @Test
    public void testXmlWithGetNodeChildren2() {

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Yanıt gövdesini String olarak al
        String responseBody = response.getBody().asString();

        // Parse the XML response
        XmlPath xmlPath = new XmlPath(responseBody);

        // Access all child elements of the projects
        NodeChildren projects = xmlPath.getNodeChildren("projects.project");
        System.out.println(projects);

        // Check the number of projects
        assertEquals(6, projects.size());

        // Check the name and id of each project
        assertEquals("A New Projectaniheeiadtatd", ((Node) projects.get(0)).get("name").toString());
        assertEquals("1", ((Node) projects.get(0)).get("id").toString());

        assertEquals("the new name aniheeiaosono", ((Node) projects.get(1)).get("name").toString());
        assertEquals("3", ((Node) projects.get(1)).get("id").toString());

        assertEquals("A New Projectaniheeiaoaees", ((Node) projects.get(2)).get("name").toString());
        assertEquals("5", ((Node) projects.get(2)).get("id").toString());

        assertEquals("A New Projectaniheeidrdhtd", ((Node) projects.get(3)).get("name").toString());
        assertEquals("6", ((Node) projects.get(3)).get("id").toString());

        assertEquals("the new name aniheeidrettt", ((Node) projects.get(4)).get("name").toString());
        assertEquals("8", ((Node) projects.get(4)).get("id").toString());

        assertEquals("A New Projectaniheeidrrhad", ((Node) projects.get(5)).get("name").toString());
        assertEquals("10", ((Node) projects.get(5)).get("id").toString());
    }
    @Test
    public void testXmlWithGetNodeChildren3() {
        String xmlendpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsxml.php";  // Replace with the actual endpoint

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Get the response body as a String
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        // Parse the XML response
        XmlPath xmlPath = new XmlPath(responseBody);

        // Access all child elements of the projects
        NodeChildren nodes = xmlPath.getNodeChildren("projects.project");

        // Check the number of projects
        assertEquals(6, nodes.size());  // Expecting 6 projects

        // Loop through each node and check the name and id
        for (io.restassured.path.xml.element.Node node : nodes.list()) {
            String name = node.get("name").toString();
            String id = node.get("id").toString();
            System.out.println("Project Name: " + name + ", Project ID: " + id);
        }

        // Check the name and id of each project
        assertEquals("Project One", nodes.get(0).get("name").toString());
        assertEquals("1", nodes.get(0).get("id").toString());

        assertEquals("Project Two", nodes.get(1).get("name").toString());
        assertEquals("3", nodes.get(1).get("id").toString());

        assertEquals("Project Three", nodes.get(2).get("name").toString());
        assertEquals("5", nodes.get(2).get("id").toString());

        assertEquals("Project Four", nodes.get(3).get("name").toString());
        assertEquals("6", nodes.get(3).get("id").toString());

        assertEquals("Project Five", nodes.get(4).get("name").toString());
        assertEquals("8", nodes.get(4).get("id").toString());

        assertEquals("Project Six", nodes.get(5).get("name").toString());
        assertEquals("10", nodes.get(5).get("id").toString());

    }

    //Using getList:
    //Here is an example showing how to use the getList method to get project names from the XML:

    @Test
    public void testXmlWithGetList() {
        String xmlendpoint = "http://example.com/api/projects";  // Replace with the actual endpoint

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Get the response body as a String
        String responseBody = response.getBody().asString();

        // Parse the XML response
        XmlPath xmlPath = new XmlPath(responseBody);

        // Get a list of project names
        List<String> projectNames = xmlPath.getList("projects.project.name");
        System.out.println(projectNames);

        // Check the number of projects
        assertEquals(3, projectNames.size());
    }
    @Test
    public void testWithGetList() {

        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(xmlendpoint).andReturn();

        // Yanıt gövdesini String olarak al
        String responseBody = response.getBody().asString();

        // Parse the XML response
        XmlPath xmlPath = new XmlPath(responseBody);

        // Access all child elements of the projects
        List<?> projects = xmlPath.getList("projects.project");
        System.out.println(projects);

        // Check the number of projects
        assertEquals(6, projects.size());  // Expecting 6 projects

        // Check the name and id of each project
        assertEquals("A New Projectaniheeiadtatd", xmlPath.getString("projects.project[0].name"));
        assertEquals("1", xmlPath.getString("projects.project[0].id"));

        assertEquals("the new name aniheeiaosono", xmlPath.getString("projects.project[1].name"));
        assertEquals("3", xmlPath.getString("projects.project[1].id"));

        assertEquals("A New Projectaniheeiaoaees", xmlPath.getString("projects.project[2].name"));
        assertEquals("5", xmlPath.getString("projects.project[2].id"));

        assertEquals("A New Projectaniheeidrdhtd", xmlPath.getString("projects.project[3].name"));
        assertEquals("6", xmlPath.getString("projects.project[3].id"));

        assertEquals("the new name aniheeidrettt", xmlPath.getString("projects.project[4].name"));
        assertEquals("8", xmlPath.getString("projects.project[4].id"));

        assertEquals("A New Projectaniheeidrrhad", xmlPath.getString("projects.project[5].name"));
        assertEquals("10", xmlPath.getString("projects.project[5].id"));
    }
    /*
    Explanation:

    1)getList Method:

    The expression xmlPath.getList("projects.project.name") returns a list of all project names.
    This method is used to retrieve simple string values and is usually sufficient for basic data.

    2)getNodeChildren Method:

    The expression xmlPath.getNodeChildren("projects.project") returns all child elements of each project node.
    The GPathResult object represents a rich structure of XML elements, making it easier to access child elements.
    This method allows you to work with more complex data while preserving the hierarchy of the XML.
    These examples show how to use XmlPath to parse XML data and use both getList and getNodeChildren methods.
    The getNodeChildren method is preferred when working with more complex XML data,
    as it allows for richer data structures and easier access to child elements.
     */
}
