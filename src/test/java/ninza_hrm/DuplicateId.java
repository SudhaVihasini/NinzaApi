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

public class DuplicateId {
	
	JavaUtility jlib=new JavaUtility();
	@Test
	
	public  void postdata() throws Throwable {
		DatabaseUtility dLib=new DatabaseUtility();
	int ranNum=	jlib.getRandomNumber();
	
		
	
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("createdBy","lithi");
	jsonobj.put("status","Created");
	jsonobj.put("projectId","NH_PROJ_7185");
	jsonobj.put("teamSize",0);
	jsonobj.put("projectName","Airtel99");
		
		

		Response resp=	given()
		.contentType(ContentType.JSON).body(jsonobj.toJSONString())
		
		.when().post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(409)
		.log().all();
		
		resp.then().statusCode(409).log().all();
		resp.then().statusLine("HTTP/1.1 409 ");
	resp.then().contentType(ContentType.JSON);
		
		resp.then().assertThat().time(Matchers.lessThan(300L));
		

		dLib.connectToDB();
	dLib.executeQueryVerifyAndGetData("select * from project",4, "Airtel99");
		
		
		

	}


}
