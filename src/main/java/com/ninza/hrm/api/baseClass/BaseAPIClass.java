package com.ninza.hrm.api.baseClass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.Fileutility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public JavaUtility jlib=new JavaUtility();
	public Fileutility flib=new Fileutility();
	public DatabaseUtility dblib=new DatabaseUtility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	@BeforeSuite
	public void configBS() throws Throwable {
		dblib.connectToDB();
		System.out.println("=======connect to DB====");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(flib.getDataFromPropetiesFile("BASEUri"));
		specReqObj=builder.build();
		
		ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		 specRespObj= resBuilder.build();
	}
	@AfterSuite
	public void configAS() throws SQLException {
		dblib.closeDbconnection();
		System.out.println("=======connect to DB====");
	}
}
