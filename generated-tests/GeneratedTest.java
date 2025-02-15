package automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetAllCountriesTest {

    @BeforeMethod
    public void setup() {
        // Setting the base URI for the API endpoint
        RestAssured.baseURI = "https://restcountries.com/v3.1";
    }

    @Test
    public void getAllCountries() throws Exception {
        // Using Rest Assured to send a GET request to /all endpoint
        String responseBody = RestAssured.given()
                .when()
                .get("/all")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        // Printing the response body
        System.out.println("Response Body: " + responseBody);
    }
}