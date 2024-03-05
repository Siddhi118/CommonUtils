package CommonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	WebDriver d=new ChromeDriver();

	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	
	@BeforeSuite
	public void BS() {
		System.out.println("Connect to Data Base");	
		
		
	}
	
	@BeforeClass
	public void BC() throws IOException {
		//it is used to lanch application
		String URL=putil.getDataFromPropertyFile("Url");
		
		
		
//		WebDriver d=new ChromeDriver();
		wutil.maximize(d);
		wutil.implicitwait(d);
			
		d.get(URL);
		
	}
	
	@BeforeMethod
	public void BM() throws IOException {
		// it is used to login to the application
		String USERNAME =putil.getDataFromPropertyFile("Username");
		String PASSWORD=putil.getDataFromPropertyFile("Password");
		
		d.findElement(By.name("user_name")).sendKeys(USERNAME);
		d.findElement(By.name("user_password")).sendKeys(PASSWORD);
		d.findElement(By.id("submitButton")).click();
		
	}
	
	@AfterClass
	public void AC() {
		// it is used to close the browser
		d.quit();
	}
	
	@AfterMethod
	public void AM() throws InterruptedException {
		// it is used to logout from appliaction
		Thread.sleep(2000);
		//moushover on image 
				WebElement image = d.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
				wutil.mousehover(d, image);		
				
				//Click on Signout
				d.findElement(By.xpath("//a[text()='Sign Out']")).click();
				
	}
	
	@AfterSuite
	public void AS() {
		
		System.out.println("Disconnect from Data Base");
		
	}
	
	

}
