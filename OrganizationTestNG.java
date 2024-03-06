package vtigecrm;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.ListnerImplimentation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

@Listeners(ListnerImplimentation.class)

public class OrganizationTestNG extends BaseClass {


	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	JavaUtil jutil = new JavaUtil();
	
	@Test
		public void OrganizationTest() throws IOException, InterruptedException {
		
		//TO read data from excel
		String ORGNAME=eutil.getDataFromExcel("Organizations", 0, 1);
		String GROUP=eutil.getDataFromExcel("Organizations", 1, 1);
		
				
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
		
		}
}
