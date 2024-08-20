package ninza_hrm;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddprojectwithoutcreatedBy {
	JavaUtility jlib=new JavaUtility();
	@Test
	
	public  void postdata() throws Throwable {
		DatabaseUtility dLib=new DatabaseUtility();
	int ranNum=	jlib.getRandomNumber();
		
		
		
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("createdBy","lithi");
	//jsonobj.put("status","Created");
	//jsonobj.put("status","OnGoing");
		jsonobj.put("status","Completed");
	
		jsonobj.put("teamSize",0);
		jsonobj.put("projectName","Airtel"+ranNum);
		
		

				String expSuccMsg = "Successfully Added";
				
		
		
	Response resp=	given()
		.contentType(ContentType.JSON).body(jsonobj.toJSONString())
		.when().post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(201)
		.log().all();
		
		resp.then().statusCode(201).log().all();
		resp.then().statusLine("HTTP/1.1 201 ");
		resp.then().contentType(ContentType.JSON);
		resp.then().header("Transfer-Encoding", "chunked");
		resp.then().assertThat().time(Matchers.lessThan(3000L));
		
     String actMsg=resp.jsonPath().get("msg");
     String projName=resp.jsonPath().get("projectName");
     String expectedProName=("Airtel"+ranNum);
     
     
     Assert.assertEquals(expSuccMsg, actMsg);
   Assert.assertEquals(expectedProName, projName);
		
  dLib.connectToDB();
	dLib.executeQueryVerifyAndGetData("select * from project",4, "Airtel"+ranNum);
	
	
	//String s=resp.getSessionId();
	//	System.out.println(s);//if u want to get the data
		

	}

	
}
