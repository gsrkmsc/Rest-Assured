package serilizationdeserilization;

import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;

//POJO----->Serilization--->----Deserilization--->POJO

public class serilizationanddeserilization {
	
	//POJO--Serilization
	@Test
	void convertPojotoJson() throws JsonProcessingException 
	
	{
		//create java object using pojo class
		User data=new User();
		data.setName("Ravi");
		data.setEmail("ravi@gmail.com");
		data.setRole("Leader");
		
	//convert java object--->json object(serilization)
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(jsondata);
	}
	
	//POJO--deSerilization
		@Test
		void convertJsontoPojo() throws JsonProcessingException 
		
		{
			String jsondata="{\r\n"
					+ "  \"name\" : \"Ravi\",\r\n"
					+ "  \"email\" : \"ravi@gmail.com\",\r\n"
					+ "  \"role\" : \"Leader\"\r\n"
					+ "}";
			
			//convert json data---->Pojo Object
			ObjectMapper  userobj=new ObjectMapper();
			User user=userobj.readValue(jsondata,User.class);
			
			System.out.println("Name"+user.getName());
			System.out.println("Email"+user.getEmail());
			System.out.println("Role"+user.getRole());
		}

}
