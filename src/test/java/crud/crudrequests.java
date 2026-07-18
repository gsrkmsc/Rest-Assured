package crud;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.util.HashMap;

public class crudrequests {

    public static String name;
    
   // @Test(priority=1)
    public void createuserhashmap() {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "jeevan1");
        requestBody.put("email", "jeevan1@gmail.com");
        requestBody.put("role", "user");

        Response response = given()
            .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
            .contentType("application/json")
            .body(requestBody)
            .log().all()
        .when()
            //.post("https://reqres.in/api/test-suite/collections/users/records")
            .post("https://reqres.in/api/users")
            
         .then()
         .log().all()
        .statusCode(201)                                // ✅ validate status code
        .body(matchesJsonSchemaInClasspath("createuser.json")) //It will validate the field of entire response it will be src test resources
        .body("name", equalTo("jeevan1"))                 // ✅ validate name
        .body("email", equalTo("jeevan1@gmail.com"))      // ✅ validate email
        .body("role", equalTo("user"))
        .extract().response();
        
     String name = response.jsonPath().getString("name");

        System.out.println("Captured userName: " + name);

        assertThat(response.jsonPath().getString("email"), equalTo(requestBody.get("email")));
        assertThat(response.jsonPath().getString("role"), equalTo(requestBody.get("role")));
    }
    
  //POJO
    // @Test(priority=2)
     public void createuserpojo() {
    
     	pojopostrequest requestbodypojo= new pojopostrequest();
     	requestbodypojo.setName("Taman");
     	requestbodypojo.setEmail("taman@gmail.com");
     	requestbodypojo.setRole("Admin");
     	

         Response response = given()
             .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(requestbodypojo)
         .when()
             .post("https://reqres.in/api/test-suite/collections/users/records")
             
          .then()
         .statusCode(201)
         .body(matchesJsonSchemaInClasspath("createuser.json"))
         .body("name", equalTo("Taman"))                 // ✅ validate name
         .body("email", equalTo("taman@gmail.com"))      // ✅ validate email
         .body("role",equalTo("Admin"))
         .log().all()                                    // ✅ print full response
         .extract().response()
         .path("id");                                    // ✅ extract id
     }
     
   //JSON Library
     //@Test(priority=3)
     public void createuserjsoblibrary() {
    
     	JSONObject requestbodyjsonobject= new JSONObject();
     	requestbodyjsonobject.put("name","rahul");
     	requestbodyjsonobject.put("email","rahul@gmail.com");
     	requestbodyjsonobject.put("role","administratory");
     	

         Response response = given()
             .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(requestbodyjsonobject.toString())     //for jsonobject tostring is compulsory
         .when()
             .post("https://reqres.in/api/test-suite/collections/users/records")
             
          .then()
         .statusCode(201)                                // ✅ validate status code
         .body("name", equalTo("rahul"))                 // ✅ validate name
         .body("email", equalTo("rahul@gmail.com"))      // ✅ validate email
         .body("role",equalTo("administratory"))
         .log().all()                                    // ✅ print full response
         .extract().response();
                                             // ✅ extract id
     }

   //External File
    // @Test(priority=4)
     public void createuserexternalfile() throws FileNotFoundException {
    
     	File f=new File("C:\\Eclipse\\Testing Practice Project\\AssuredAPI\\src\\test\\resources\\createuserexternal.json");
     	FileReader fr=new FileReader(f);
     	JSONTokener jt=new JSONTokener(fr);
     	JSONObject data=new JSONObject(jt);
     	

         given()
             .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(data.toString())     //for jsonobject tostring is compulsory
         .when()
             .post("https://reqres.in/api/test-suite/collections/users/records")
             
          .then()
         .statusCode(201)                                // ✅ validate status code
         .body("name", equalTo("Robert"))                 // ✅ validate name
         .body("email", equalTo("Robert@gmail.com"))      // ✅ validate email
         .body("role",equalTo("accountant"))              //validated role
         .log().all()                                    // ✅ print full response
         .extract().response();
                                            
     }

    @Test(priority=1)
    public void getUsers() {
        given()
            .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
        .when()
            .get("https://reqres.in/api/users")
        .then()
            .statusCode(200)
            //.body("name", equalTo(name))
            .log().all();
    }

    
      
	

	//@Test(priority=6)
    public void updateuser() {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "madhu");
        requestBody.put("email", "madhu@gmail.com");

        given()                                          
            .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
            .contentType("application/json")
            .body(requestBody)
        .when()
            .put("https://reqres.in/api/test-suite/collections/users/records/" + name)
        .then()
            .statusCode(200)
            .log().all();                                 
    }
    
    //@Test(priority=7)
    
    public void deleteuser() {
    	
    	given()
        .header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
    .when()
        .delete("https://reqres.in/api/test-suite/collections/users/records/" + name)
    .then()
        .statusCode(204)   // DELETE returns 204 No Content
        .log().all();
    }
}