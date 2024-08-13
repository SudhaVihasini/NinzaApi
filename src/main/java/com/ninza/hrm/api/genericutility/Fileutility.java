package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Fileutility {
	public String getDataFromPropetiesFile(String key) throws Throwable
	{
		FileInputStream fis = new  FileInputStream("./config_env_data/configEnvdata.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String data=pobj.getProperty(key);

	return data;
	}
	

}
