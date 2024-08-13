package ninza_hrm;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Addproject {
	
	@Test
	public  void postdata() {
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("createdBy","lithi");
		jsonobj.put("status","Created");
		jsonobj.put("teamSize",0);
		jsonobj.put("projectName","Airtel 988");
		
		
		given()
		.contentType(ContentType.JSON).body(jsonobj.toJSONString())
		.when().post("http://49.249.28.218:8091/addProject")
		.then()
		.assertThat().statusCode(201)
		.log().all();
		
		
	}

}
