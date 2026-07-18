package xmlschemavalidation;
import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class xmlschemavalidation {
	@Test
	
	public void xmlschemavalidation() {
		
		given()
        .when()
        .get("https://mocktarget.apigee.net/xml")
        .then()
        .statusCode(200)
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("getuser.xsd"));
	}
	
	

}
