package testNGpractice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.ObjectRepository.LoginPage;


public class LoginWithDataProvider {

	@Test(dataProviderClass = DataProviderdata.class , dataProvider = "loginData")
	public void getData(String username, String password)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(username, password);
	}
}

