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

public class Addprowithnull {
	 
	
	JavaUtility jlib=new JavaUtility();
	@Test
	
	public  void postdata() throws Throwable {
		DatabaseUtility dLib=new DatabaseUtility();
	int ranNum=	jlib.getRandomNumber();
	
	ProjectPojo pobj = new ProjectPojo("indigo", "Created", "",  0);
		
	      Response resp=	given()
		.contentType(ContentType.JSON).body(pobj)
		.when().post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(502)
		.log().all();
		

	

}}
