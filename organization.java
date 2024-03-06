package vtigecrm;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.ListnerImplimentation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

@Listeners(ListnerImplimentation.class)
public class organization {

	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	JavaUtil jutil = new JavaUtil();
	
	@Test
		public void OrganizationTest() throws IOException, InterruptedException {
		WebDriver d=new ChromeDriver();
		wutil.maximize(d);
		wutil.implicitwait(d);
		//TO read Data From Property File.
		String URL=putil.getDataFromPropertyFile("Url");

		
		String USERNAME =putil.getDataFromPropertyFile("Username");
	
		String PASSWORD=putil.getDataFromPropertyFile("Password");
		
		//TO read data from excel
		String ORGNAME=eutil.getDataFromExcel("Organizations", 0, 1);
		String GROUP=eutil.getDataFromExcel("Organizations", 1, 1);
		
		//To launch the application
		d.get(URL);
		
		// Login to application 

		d.findElement(By.name("user_name")).sendKeys(USERNAME);
		d.findElement(By.name("user_password")).sendKeys(PASSWORD);
		d.findElement(By.id("submitButton")).click();
		
		// click on organization
		Thread.sleep(2000);
		d.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//Click on Create Organization..(+)
		d.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Enter Organization Name
		d.findElement(By.name("accountname")).sendKeys(ORGNAME+jutil.getRandomNumber());
		
		//In AssignedTo Click on Group
		d.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
		
		//Identify targeted element webelement-month
		WebElement element=d.findElement(By.name("assigned_group_id"));
		wutil.handeldropdown(element,GROUP);
		
		d.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		Thread.sleep(2000);
		
		//moushover on image 
		WebElement image = d.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mousehover(d, image);		
		
		//Click on Signout
		d.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		}
}
