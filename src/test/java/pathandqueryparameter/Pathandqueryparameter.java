package pathandqueryparameter;
import org.testng.annotations.Test;
//Imports BDD core keywords like given(), when(), then(), get(), post()
import static io.restassured.RestAssured.*;

//Imports validation matchers like matchesJsonSchemaInClasspath()
import static io.restassured.matcher.RestAssuredMatchers.*;

//Imports Hamcrest assertion matchers like equalTo(), containsString(), hasItem()
import static org.hamcrest.Matchers.*;


public class Pathandqueryparameter {
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	
	void testqueryandpathparameters()
	
	{
		given()
		.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
		.pathParam("mypath","users")  //path parameters
		.queryParam("page",1)  //query parameter
		.queryParam("id",5)    //query parameter
		
		.when()
		  .get("https://reqres.in/api/{mypath}")
		  
		 .then()
		   .statusCode(200)
		   .log().all();
	}

}
