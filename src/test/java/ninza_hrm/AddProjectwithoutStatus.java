package ninza_hrm;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddProjectwithoutStatus {
	
	JavaUtility jlib=new JavaUtility();
	@Test
	
	public  void postdata() throws Throwable {
		DatabaseUtility dLib=new DatabaseUtility();
	int ranNum=	jlib.getRandomNumber();
	
	ProjectPojo pobj = new ProjectPojo("indigo", "", "lithi",  0);
	String expSuccMsg = "Successfully Added";
	
	 Response resp=	given()
				//.contentType(ContentType.JSON).body(JSONObject.toJSONString(jsonobj))
				.contentType(ContentType.JSON).body(pobj)
				.when().post("http://49.249.28.218:8091/addProject");
				resp.then()
				.assertThat().statusCode(502)
				.log().all();
				
				
//				resp.then().contentType(ContentType.JSON);
//				
//				resp.then().assertThat().time(Matchers.lessThan(3000L));
//				
//				 String actMsg=resp.jsonPath().get("msg");
//			     String projName=resp.jsonPath().get("projectName");
//			     String expectedProName=("Airtel"+ranNum);
//			     
//			     Assert.assertEquals(expSuccMsg, actMsg);
//			   Assert.assertEquals(expectedProName, projName);
//					
//			  dLib.connectToDB();
//				dLib.executeQueryVerifyAndGetData("select * from project",4, "Airtel"+ranNum);

			

		}}



