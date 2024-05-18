// Parse the JSON response directly from the Response object
JsonPath jsonPath = response.jsonPath();

// Use JsonPath.from() to parse the JSON response from the string
JsonPath jsonPath = JsonPath.from(responseBody);

// Use the JsonPath library to parse the response
JsonPath jsonPath = new JsonPath(response);