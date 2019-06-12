package phptravels.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	public static Properties getProperties(String filePath, String environment) {
		Properties propFile=new Properties();
		try {
			FileInputStream fileProp = new FileInputStream(filePath+environment+".properties");	
			
			propFile.load(fileProp);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return propFile;
	}
}
