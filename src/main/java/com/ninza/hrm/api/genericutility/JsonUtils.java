package com.ninza.hrm.api.genericutility;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtils {
	Fileutility flib=new Fileutility();
	public String getDataonJsonPath(Response resp,String jsonXpath) {
		List<Object> list=JsonPath.read(resp.asString(), jsonXpath);
		
		return list.get(0) .toString();
	}
	
	public String getDataonXpathPath(Response resp,String xmlXpath) {
	return resp.xmlPath().get(xmlXpath);
	}
	public boolean VerifyDataOnJsonPath	(Response resp,String jsonXpath,String expectedData)
	{
		List<String> list=JsonPath.read(resp.asString(), jsonXpath);
		boolean flag=false;
		for(String str:list)
			{
			if(str.equals(expectedData)) {
			System.out.println(expectedData + "is available==PASS");
			flag=true;
		}
		}
		if(flag==false) {
			System.out.println(expectedData + "is not available==PASS");
	}
	
	return flag;
	
}
	public String getAccessToken() throws Throwable {
	Response resp=	given()
			.formParam("client_id", flib.getDataFromPropetiesFile("ClientID"))
			.formParam("client_secret",flib.getDataFromPropetiesFile("ClientSecret"))
			.formParam("grant_type", "client_credentials")
			
			.when()
			.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		resp.then().log().all();
			
			//Capture the token from the response
		String token= resp.jsonPath().getString("access_token");
		return token;
}}