package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CountryAPITest {

    @BeforeMethod
    public void setup() {
        // Setting the base URI for the API
        RestAssured.baseURI = "https://restcountries.com/v3.1";
    }

    @Test
    public void getAllCountriesPositiveTest() {
        // Positive test to verify that the GET /all endpoint returns a 200 status code
        System.out.println("Running positive test to get all countries");
        given()
                .when()
                .get("/all")
                .then()
                .statusCode(200);
        
        // Printing the response body
        System.out.println("Response body: " + get("/all").body().prettyPrint());
    }

    @Test
    public void getAllCountriesNegativeTest() {
        // Negative test to verify that an invalid endpoint returns a 404 status code
        System.out.println("Running negative test with invalid endpoint");
        given()
                .when()
                .get("/all/invalid")
                .then()
                .statusCode(404);
        
        // Printing the response body
        System.out.println("Response body: " + get("/all/invalid").body().prettyPrint());
    }
}