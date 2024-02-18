package DataDrivenConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class jxl_file {
	
	String [][]data=null;
	
	@DataProvider(name="loginData")
	public String[][] loginprovider() throws BiffException, IOException {
		data = getExcel();
		return data;
	}
	
	public String[][] getExcel() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream("C:\\Udhaya Selinium\\Selinium WorkSpace\\LearningSelenium\\TestData\\Orange_TestData.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowcount = sheet.getRows();
		int colcount = sheet.getColumns();
		
		String testData[][] = new String[rowcount-1][colcount];
		
		for(int i=1;i<rowcount;i++) {
			for(int j=0;j<colcount;j++) {
				testData[i-1][j] = sheet.getCell(j, i).getContents();
//we are taking the data from the excel structure and Storing it in  the two dim matrix (testData[i-1][j])
																	
			}
		}
		return testData;
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
