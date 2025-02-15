package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetAllCountriesTest {

    @BeforeMethod
    public void setup() {
        // Setting the base URI for the API endpoint
        RestAssured.baseURI = "https://restcountries.com/v3.1";
    }

    @Test
    public void getAllCountriesTest() {
        // Using Rest Assured to send a GET request to the /all endpoint
        given()
                .when()
                .get("/all")
                .then()
                .log().all() // Print the response in the console
                .assertThat()
                .statusCode(200); // Verify the status code is 200
    }
}