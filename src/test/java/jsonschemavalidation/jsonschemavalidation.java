package jsonschemavalidation;

import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;

public class jsonschemavalidation {
	
	@Test(priority=1)
	
	public void jsonschemaget() {
		
		given()
        .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
        .when()
        .get("https://reqres.in/api/test-suite/collections/users")
        .then()
        .statusCode(200)
        .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getuserjsonschemavalidator.json")) //JsonSchemaValidator.matchesJsonSchemaInClasspath is a class to validate the json schema and getuserjsonschemavalidator.json is defined in src test resources
	    .log().all();
	
	}
	
//@Test(priority=2)
	
	public void jsonschemapost() {
		
		given()
        .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
        .contentType("application/json")
        .body("{ \"name\": \"josh\", \"email\": \"josh@gmail.com\",\"role\": \"staff\" }")
        .when()
        .post("https://reqres.in/api/test-suite/collections/users/records")
        .then()
        .statusCode(201)
        .body("name", equalTo("josh"))                 
        .body("email", equalTo("josh@gmail.com"))
        .body("role", equalTo("staff"))
        .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createuserjsonschemavalidationr.json")) //JsonSchemaValidator.matchesJsonSchemaInClasspath is a class to validate the json schema and createuserjsonschemavalidationr.json is defined in src test resources
        .log().all();
	
	}
}
