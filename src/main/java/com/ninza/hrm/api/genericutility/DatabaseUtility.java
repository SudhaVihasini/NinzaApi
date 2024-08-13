
package com.ninza.hrm.api.genericutility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	static Connection con=null;
	static ResultSet result=null;
	static Fileutility flib=new Fileutility();
	
	public static void connectToDB() throws Throwable {
	Driver driverRef;
		
	try {
		driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
	  con=DriverManager.getConnection(flib.getDataFromPropetiesFile("DBUrl"),flib.getDataFromPropetiesFile("DB_Username"),flib.getDataFromPropetiesFile("DB_Password"));
	}catch (Exception e) {
}

}
	
	
public void closeDbconnection()throws SQLException{
try	{
		con.close();
	}catch (Exception e) {
}
}
public static ResultSet executeQuery(String query)throws Throwable{

	try {
		
		result=con.createStatement().executeQuery(query);
		return result;
	}catch(Exception e) {
	}finally {
	}
	return result;
	}
	



//public boolean  executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException{
//
//boolean flag=false;
//result=con.createStatement().executeQuery(query);
//
//	while(result.next()) {
//		if(result.getString(columnIndex).equals(expectedData)) {
//			flag=true;
//		}	break;
//	
//	}
//
//if(flag) {
//	System.out.println(expectedData+"====>data verified in data base table");
//return true;
//}else {
//	System.out.println(expectedData+"====>data verified in not data base table");
//	return false;
//	
//}
//
//}
public boolean  executeQueryVerifyAndGetData(String query ,int columnIndex , String expectedData) throws Throwable{
    boolean flag = false;
result = con.createStatement().executeQuery(query);

while (result.next()) {
if(result.getString(columnIndex).equals(expectedData)) {
flag= true;
break;
}
}
if(flag) {
System.out.println(expectedData + "===> data verified in data base table");
return true;
}else {
System.out.println(expectedData + "===> data not verified in data base table");
return false;
}


}

}		