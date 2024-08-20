package ninza_hrm;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DuplicateData {
	
	JavaUtility jlib=new JavaUtility();
	@Test
	
	public  void postdata() {
		DatabaseUtility dLib=new DatabaseUtility();
	int ranNum=	jlib.getRandomNumber();
		
	ProjectPojo pobj = new ProjectPojo("Airtel99", "Completed", "lithi",  0);
		
		
//		JSONObject jsonobj =new JSONObject();
//		jsonobj.put("createdBy","lithi");
//	
//		jsonobj.put("status","Completed");
//	
//		jsonobj.put("teamSize",0);
//		jsonobj.put("projectName","Airtel99");
		
		

				String expSuccMsg = "The Project Name :Airtel99 Already Exists";
				
		
		
	Response resp=	given()
		//.contentType(ContentType.JSON).body(jsonobj.toJSONString(jsonobj))
		.contentType(ContentType.JSON).body(pobj)
		.when().post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(409)
		.log().all();
		
		resp.then().statusCode(409).log().all();
		resp.then().statusLine("HTTP/1.1 409 ");
	resp.then().contentType(ContentType.JSON);
		
		resp.then().assertThat().time(Matchers.lessThan(300L));
		
     String actMsg=resp.jsonPath().get("msg");

     
     Assert.assertEquals(expSuccMsg, actMsg);

		

}}
