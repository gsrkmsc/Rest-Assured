package crud;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import net.datafaker.Faker;

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
    public static String Id;
    
    @Test(priority=1)
    public void createuserhashmap() {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "jeevan1");
        requestBody.put("email", "jeevan1@gmail.com");
        requestBody.put("role", "user");

        Response response =
        given()
        //.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("http://localhost:3000/users")
            //.post("https://reqres.in/api/test-suite/collections/users/records")
        .then()
            .statusCode(201)
            .log().all()
            .extract().response();

        Id = response.jsonPath().getString("id");

        System.out.println("Created userId: " + Id);
    }
    
  //POJO
     @Test(priority=2)
     public void createuserpojo() {
    
    	 Faker faker = new Faker();    //faker will add random value and dependency added in pom.xml and faker is imported import net.datafaker.Faker

    	 pojopostrequest requestbodypojo = new pojopostrequest();
    	 requestbodypojo.setName(faker.name().fullName());
    	 requestbodypojo.setEmail(faker.internet().emailAddress());
    	 requestbodypojo.setRole(faker.job().position());   // or pick from a fixed list, see below
     	

         Response response = given()
             //.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(requestbodypojo)
         .when()
               .post("http://localhost:3000/users")
             //.post("https://reqres.in/api/test-suite/collections/users/records")
             
               .then()
               .statusCode(201)
               .body(matchesJsonSchemaInClasspath("createuser.json"))
               .body("name", equalTo(requestbodypojo.getName()))
               .body("email", equalTo(requestbodypojo.getEmail()))
               .log().all()
               .extract().response();

           Id = response.jsonPath().getString("id");   // ← extract id separately, into your String field

           System.out.println("Created userId (pojo): " + Id);
     }
     
   //JSON Library
     @Test(priority=3)
     public void createuserjsoblibrary() {
    	 
    	 
    
     	JSONObject requestbodyjsonobject= new JSONObject();
     	requestbodyjsonobject.put("name","rahul");
     	requestbodyjsonobject.put("email","rahul@gmail.com");
     	requestbodyjsonobject.put("role","administratory");
     	

         Response response = given()
             //.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(requestbodyjsonobject.toString())     //for jsonobject tostring is compulsory
         .when()
               .post("http://localhost:3000/users")
             //.post("https://reqres.in/api/test-suite/collections/users/records")
             
          .then()
         .statusCode(201)                                // ✅ validate status code
         .body("name", equalTo("rahul"))                 // ✅ validate name
         .body("email", equalTo("rahul@gmail.com"))      // ✅ validate email
         .body("role",equalTo("administratory"))
         .log().all()                                    // ✅ print full response
         .extract().response();
                                             
     }

   //External File
     @Test(priority=4)
     public void createuserexternalfile() throws FileNotFoundException {
    
     	File f=new File("C:\\Eclipse\\Testing Practice Project\\AssuredAPI\\src\\test\\resources\\createuserexternal.json");
     	FileReader fr=new FileReader(f);
     	JSONTokener jt=new JSONTokener(fr);
     	JSONObject data=new JSONObject(jt);
     	

         given()
             //.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
             .contentType("application/json")
             .body(data.toString())     //for jsonobject tostring is compulsory
         .when()
             .post("http://localhost:3000/users")
             //.post("https://reqres.in/api/test-suite/collections/users/records")
             
          .then()
         .statusCode(201)                                // ✅ validate status code
         .body("name", equalTo("Robert"))                 // ✅ validate name
         .body("email", equalTo("Robert@gmail.com"))      // ✅ validate email
         .body("role",equalTo("accountant"))              //validated role
         .log().all()                                    // ✅ print full response
         .extract().response();
                                            
     }

     @Test(priority=5)
     public void getUsers() {
         Response response = 
         given()
             //.header("x-api-key", "free_user_3EieSBamYR9arNNtcCUWPnxSUXp")
         .when()
             .get("http://localhost:3000/users")
         .then()
             .statusCode(200)
             .log().all()
             .extract().response();

         // dynamically grab the first user's id from the response
         
         
         //Id = response.jsonPath().getString("[0].id");

         //System.out.println("Extracted userId: " + Id);
         
     }

    
      
	

     @Test(priority=6, dependsOnMethods = "createuserhashmap")
     public void updateuser() {

         HashMap<String, String> requestBody = new HashMap<>();
         requestBody.put("id", Id);
         requestBody.put("name", "madhu@gmail.com");
         requestBody.put("email", "madhu@gmail.com");
         requestBody.put("role", "clerk");

         given()
             .contentType("application/json")
             .body(requestBody)
         .when()
             .put("http://localhost:3000/users/" + Id)
         .then()
             .statusCode(200)
             .log().all();
     }

     @Test(priority=7, dependsOnMethods = "createuserhashmap")
     public void deleteuser() {

         given()
         .when()
             .delete("http://localhost:3000/users/" + Id)
         .then()
             .statusCode(200)
             .log().all();
     }
}