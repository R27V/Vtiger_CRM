package login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class WriteDataIntoPropertyFile {

	public static void main(String[] args) throws Throwable {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		Properties pObj = new Properties();
		pObj.load(fis);
		
		pObj.setProperty("browser", "chrome");
		pObj.setProperty("url", "http://localhost:8888/");
		pObj.setProperty("username", "admin");
		pObj.setProperty("password", "admin");
		
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\CommonData.properties");
		
		pObj.store(fos, "Data Added");
	}
}
