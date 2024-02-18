package DataDrivenConcept;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_DataProviders {
	
	String [][]data= {
			{"admin","admin"},
			{"Admin","admin"},
			{"admin","admin123"},
			{"Admin","admin123"}
		};
	
	@DataProvider(name="loginData")
	public String[][] testData() {
		
		return data;
	}
	
	

	@Test(dataProvider = "loginData")
	public void login(String username, String password) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	
}
