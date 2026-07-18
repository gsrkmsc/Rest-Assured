package postrequest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class waystocreatepostrequestbody {

    public static String createdStudentId;  // global variable

    // 1) Post request body using HashMap
    @Test(priority=1)
    void testPostusingHashMap() {

        HashMap data = new HashMap();
        data.put("name", "scott");
        data.put("location", "France");

        String courseArr[] = {"C", "C++"};
        data.put("courses", courseArr);

        createdStudentId =
            given()
                .contentType("application/json")
                .body(data)
            .when()
                .post("http://localhost:3000/students")
            .then()
                .statusCode(201)
                .body("name", equalTo("scott"))
                .body("location", equalTo("France"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all()
                .extract().path("id");

        System.out.println("Created Student ID: " + createdStudentId);
    }

    @Test(priority=2)
    void testDelete1() {

        System.out.println("Deleting Student ID: " + createdStudentId);

        given()
        .when()
            .delete("http://localhost:3000/students/" + createdStudentId)
        .then()
            .statusCode(200)
            .log().all();
    }

    // 2) Post request body using org.json library
    @Test(priority=3)
    void testPostusingJsonLibrary() {

        JSONObject data = new JSONObject();
        data.put("name", "scott");        
        data.put("location", "france");

        String coursesArr[] = {"C", "C++"};
        data.put("courses", coursesArr);  

        createdStudentId =                
            given()
                .contentType("application/json")
                .body(data.toString())
            .when()
                .post("http://localhost:3000/students")
            .then()
                .statusCode(201)
                .body("name", equalTo("scott"))      
                .body("location", equalTo("france")) 
                .body("courses[0]", equalTo("C"))    
                .body("courses[1]", equalTo("C++"))  
                .header("Content-Type", "application/json")
                .log().all()
                .extract().path("id");        

        System.out.println("Created Student ID: " + createdStudentId);
    }

    @Test(priority=4)
    void testDelete2() {

        System.out.println("Deleting Student ID: " + createdStudentId);

        given()
        .when()
            .delete("http://localhost:3000/students/" + createdStudentId)
        .then()
            .statusCode(200)
            .log().all();
    }
    
 // 3) Post request body using POJO class
    
    @Test(priority=5)
    void testPostusingPOJO() {

        pojo data=new pojo();
        data.setName("scott");
        data.setLocation("france");
        
        String coursesArr[]= {"C","C++"};
        data.setCourses(coursesArr);

        createdStudentId =                
            given()
                .contentType("application/json")
                .body(data)
            .when()
                .post("http://localhost:3000/students")
            .then()
                .statusCode(201)
                .body("name", equalTo("scott"))      
                .body("location", equalTo("france")) 
                .body("courses[0]", equalTo("C"))    
                .body("courses[1]", equalTo("C++"))  
                .header("Content-Type", "application/json")
                .log().all()
            .extract().path("id");        

        System.out.println("Created Student ID: " + createdStudentId);
    }
    @Test(priority=6)
    void testDelete3() {

        System.out.println("Deleting Student ID: " + createdStudentId);

        given()
        .when()
            .delete("http://localhost:3000/students/" + createdStudentId)
        .then()
            .statusCode(200)
            .log().all();
    }
    
// 4) Post request body using external j-son file
    
    @Test(priority=7)
    void testexternaljsonfile() throws FileNotFoundException {

       File f=new File(".\\student.json");
       
       FileReader fr=new FileReader(f);
       JSONTokener jt=new JSONTokener(fr);
       JSONObject data=new JSONObject(jt);

        createdStudentId =                
            given()
                .contentType("application/json")
                .body(data.toString())
            .when()
                .post("http://localhost:3000/students")
            .then()
                .statusCode(201)
                .body("name", equalTo("scott"))      
                .body("location", equalTo("france")) 
                .body("courses[0]", equalTo("C"))    
                .body("courses[1]", equalTo("C++"))  
                .header("Content-Type", "application/json")
                .log().all()
                .extract().path("id");        

        System.out.println("Created Student ID: " + createdStudentId);
    }
    @Test(priority=8)
    void testDelete4() {

        System.out.println("Deleting Student ID: " + createdStudentId);

        given()
        .when()
            .delete("http://localhost:3000/students/" + createdStudentId)
        .then()
            .statusCode(200)
            .log().all();
    }
}