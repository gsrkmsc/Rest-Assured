package pathandqueryparameter;
import org.testng.annotations.Test;

import io.restassured.response.Response;  


import io.restassured.response.ResponseOptions;

//Imports BDD core keywords like given(), when(), then(), get(), post()
import static io.restassured.RestAssured.*;

//Imports validation matchers like matchesJsonSchemaInClasspath()
import static io.restassured.matcher.RestAssuredMatchers.*;

//Imports Hamcrest assertion matchers like equalTo(), containsString(), hasItem()
import static org.hamcrest.Matchers.*;

public class cookiesandheaders {
	
	//@Test(priority=1)
	
	void cookiesandheader()
	
	{
		given()
		
		.when()
		  .get("https://www.google.com")
		  
		 .then()
		   .cookie("AEC","AaJma5uBR02PFMmQ75UB0t_0L-Cww7Sn9qAX567M4GKRe6dhRJJ0KvsucS0")
		  .log().all();
	}
	
   @Test(priority=2)
	
	void getcookies()
	
	{
		Response res= given()
		
		.when()
		  .get("https://www.google.com");
		
		//get single cookie info
		
		String cookie_value=res.getCookie("AEC");
		System.out.println("The Value of the cookie is:" +cookie_value);
		  
		 
	}

}
