package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetApiTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void updateExistingPet() {
        // Update an existing pet
        String updatePetJson = "{\"id\": 1, \"category\": {\"id\": 1, \"name\": \"dogs\"}, " +
                "\"name\": \"doggie\", \"photoUrls\": [\"string\"], " +
                "\"tags\": [{\"id\": 1, \"name\": \"string\"}], \"status\": \"sold\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatePetJson)
                .when()
                .put("/pet");

        System.out.println("Update Pet Response: " + response.prettyPrint());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void addNewPet() {
        // Add a new pet to the store
        String newPetJson = "{\"id\": 999, \"category\": {\"id\": 1, \"name\": \"dogs\"}, " +
                "\"name\": \"newDoggie\", \"photoUrls\": [\"string\"], " +
                "\"tags\": [{\"id\": 1, \"name\": \"string\"}], \"status\": \"available\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(newPetJson)
                .when()
                .post("/pet");

        System.out.println("Add Pet Response: " + response.prettyPrint());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void findPetsByStatus() {
        // Find pets by status - Positive test
        Response response = given()
                .when()
                .param("status", "sold")
                .get("/pet/findByStatus");

        System.out.println("Find Pets by Status Response: " + response.prettyPrint());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void findPetsByInvalidStatus() {
        // Find pets by status - Negative test with invalid status
        Response response = given()
                .when()
                .param("status", "invalid_status")
                .get("/pet/findByStatus");

        System.out.println("Find Pets by Invalid Status Response: " + response.prettyPrint());
        assertEquals(response.getStatusCode(), 200);
    }
}