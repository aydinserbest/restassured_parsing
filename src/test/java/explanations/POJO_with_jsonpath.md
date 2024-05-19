JSON Parsing and Domain Object Mapping with REST Assured and JsonPath

In addition to parsing the JSON response using JsonPath, 
you can also directly map the response to domain objects (Java POJOs - Plain Old Java Objects). 
This process is also considered parsing, as the goal in both cases is 
to convert JSON data into a structure that can be processed by the program.

Mapping JSON Response to Domain Objects
Mapping a JSON response directly to a domain object involves converting JSON data to Java objects. 
This is known as object mapping and falls under JSON parsing 
because it transforms JSON data into a processable format.

Example
Below is an example of mapping a JSON response directly to a domain object and performing validation on this object.

Domain Object (POJO)
First, create a Java class (POJO) to represent the JSON response.

    public class Project {
    private int id;
    private String name;
    private int position;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    
        // Getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public int getPosition() { return position; }
        public void setPosition(int position) { this.position = position; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
        
        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    }
Mapping and Validating the API Response
Using REST Assured, you can map the API response directly to a Project object and perform validation on it.

    public class RetrieveResponse {
    String jsonEndpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";
    
        @Test
        public void testDomainObjectParsingAndAssertion() {
            // Send an HTTP GET request and get the response
            Response response = RestAssured.when().get(jsonEndpoint);
    
            // Parse the JSON response into a Project array
            Project[] projects = response.jsonPath().getObject("projects.project", Project[].class);
    
            // Access the first project
            Project firstProject = projects[0];
    
            // Validate the first project's name
            assertThat(firstProject.getName(), equalTo("A New Projectaniheeiadtatd"));
        }
    }

Parsing Terminology
JsonPath Parsing:         Using JsonPath to parse the JSON response and access specific fields.
Domain Object Mapping:    Directly converting the JSON response to Java objects (domain objects).

Both methods fall under JSON parsing because they both transform JSON data into a format 
that can be processed by the program.

Summary
JsonPath Usage: Allows parsing the JSON response to access specific fields.
Domain Object Usage: Maps the JSON response directly to Java POJOs, transforming JSON data into Java objects.
Both methods are considered JSON parsing and provide different approaches to processing JSON data.


### above code, THERE IS AN EXPRESSION: Project[] projects
Project[] vs. List<Project>
The expression Project[] projects represents an array of Project objects. 
However, processing the JSON response as a list is generally more common and practical. 
In such cases, you can use List<Project>. 
Below is how you can parse a JSON response into a List<Project> and use it.

Converting API Response to List<Project> and Validation
To convert an API response directly into a List<Project> object using REST Assured, you can use TypeRef. 
This is a common way to map a JSON response to a Java collection.

Domain Object (POJO)
First, create a Java class (POJO) to represent the JSON response (this part remains unchanged):

    public class Project {
    private int id;
    private String name;
    private int position;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    }
Converting and Validating the API Response as List<Project>
To get the API response as a List<Project>:

    public class RetrieveResponse {
    String jsonEndpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void testDomainObjectListParsingAndAssertion() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the JSON response into a List<Project>
        List<Project> projects = response.jsonPath().getObject("projects.project", new TypeRef<List<Project>>() {});

        // Access the first project
        Project firstProject = projects.get(0);

        // Validate the first project's name
        assertThat(firstProject.getName(), equalTo("A New Projectaniheeiadtatd"));
    }
    }
Explanations
1-TypeRef Usage: 
By using TypeRef, you can convert the JSON response into Java collections. 
The expression new TypeRef<List<Project>>() {} ensures that the response is parsed into a List<Project>.

2-List<Project>: 
Maps the JSON response to a List<Project> object, meaning a list of Project objects.

Summary
Getting JSON Response as List<Project>: 
Using TypeRef, you can map the JSON response to Java collections. This method is useful for processing JSON data as a list.
Accessing and Validating Specific Fields: 
Using List<Project>, you can access specific fields within the JSON response and validate these fields.
This method is effective for converting JSON responses to Java objects and processing these objects. 
Both arrays (Project[]) and lists (List<Project>) can be used, 
but lists generally provide more flexibility and are a more common approach.

Comparing new TypeRef<List<Project>>() {} and Project[].class
Both new TypeRef<List<Project>>() {} and Project[].class can be used to achieve the same result, 
but they work differently and have different uses.

Differences:

1-new TypeRef<List<Project>>() {}:

Scope: This method is used specifically for converting JSON responses to Java collections (e.g., List<Project>).
Usage: It is used by libraries like Jackson or REST Assured to map JSON data to complex Java types (generic types).
Example:

    List<Project> projects = response.jsonPath().getObject("projects.project", new TypeRef<List<Project>>() {});

2-Project[].class:

Scope: This method is used to convert JSON responses to a Java array (Project[]).
Usage: It is commonly used to map JSON data to a specific Java type. It is straightforward for mapping array types.
Example:

    Project[] projects = response.jsonPath().getObject("projects.project", Project[].class);

Examples:
Getting JSON Response as List<Project>:

    public class RetrieveResponse {
    String jsonEndpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void testDomainObjectListParsingAndAssertion() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the JSON response into a List<Project>
        List<Project> projects = response.jsonPath().getObject("projects.project", new TypeRef<List<Project>>() {});

        // Access the first project
        Project firstProject = projects.get(0);

        // Validate the first project's name
        assertThat(firstProject.getName(), equalTo("A New Projectaniheeiadtatd"));
    }
    }

Getting JSON Response as Project[]:

    public class RetrieveResponse {
    String jsonEndpoint = "https://testpages.herokuapp.com/apps/mocktracks/projectsjson.php";

    @Test
    public void testDomainObjectArrayParsingAndAssertion() {
        // Send an HTTP GET request and get the response
        Response response = RestAssured.when().get(jsonEndpoint);

        // Parse the JSON response into a Project array
        Project[] projects = response.jsonPath().getObject("projects.project", Project[].class);

        // Access the first project
        Project firstProject = projects[0];

        // Validate the first project's name
        assertThat(firstProject.getName(), equalTo("A New Projectaniheeiadtatd"));
    }
    }
Summary:
TypeRef<List<Project>>:

Usage: For generics (generic types).
Advantage: Can map JSON data to complex collection types (e.g., List<Project>).
Use Case: Useful when you need to convert JSON data to a Java collection.

Project[].class:

Usage: For array types.
Advantage: Can map JSON data to a specific Java array type.
Use Case: Useful when you need to convert JSON data to a Java array.

Both methods can be used to convert JSON data to Java objects, 
but TypeRef provides more flexibility and is more suitable for working with generics. 
Project[].class is simpler and more direct for array conversions. 
Depending on your needs, you can choose either method.







