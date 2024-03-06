package vtigecrm;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.ListnerImplimentation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;
@Listeners(ListnerImplimentation.class)

public class contact {
	

	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	JavaUtil jutil = new JavaUtil();
	
	@Test
		public void ContactTest() throws IOException, InterruptedException {
		WebDriver d=new ChromeDriver();
		// to maximize the window
		wutil.maximize(d);
		// toapplay implicite wait for findelemet()
		wutil.implicitwait(d);
		
		//TO read Data From Property File.
		String URL=putil.getDataFromPropertyFile("Url");
		String USERNAME =putil.getDataFromPropertyFile("Username");
		String PASSWORD=putil.getDataFromPropertyFile("Password");
		
//		Thread.sleep(2000);
		// to launch application 
		d.get(URL);
		
		// login to application
		d.findElement(By.name("user_name")).sendKeys(USERNAME);
		d.findElement(By.name("user_password")).sendKeys(PASSWORD);
		d.findElement(By.id("submitButton")).click();	
		
		
		//to click on contact
		d.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		// to click on create contact (+)
		d.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
			
		//TO read data from excel
		String FNAME=eutil.getDataFromExcel("Contacts", 0, 1);
		String LNAME=eutil.getDataFromExcel("Contacts", 1, 1);
		String GROUP = eutil.getDataFromExcel("Contacts", 2, 1);
		String ORGNAME = eutil.getDataFromExcel("Contacts", 3, 1);
		
		// enter first and last name 
		d.findElement(By.name("firstname")).sendKeys(FNAME);
		d.findElement(By.name("lastname")).sendKeys(LNAME);
		
		// to fail the text script
		String actualurl = d.getCurrentUrl();
		String exceptedurl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		Assert.assertEquals(actualurl, exceptedurl);
		
		// click on radio button 
		d.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		// to select dropdown 
		WebElement dropdown = d.findElement(By.name("assigned_group_id"));
		wutil.handeldropdown(dropdown, GROUP);
		
		
		// click on organization name text feild (+)
		d.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		
		
		wutil.switchwindow(d,"http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		
		
		// to enter organization name in search textfeild 
		d.findElement(By.id("search_txt")).sendKeys(ORGNAME);		
		
		// to click on search now button 
		d.findElement(By.name("search")).click();
		
		// clickk on organization name 
		d.findElement(By.xpath("//a[text()='Intel2']")).click();
		
		
		//to transfer the window from child to parent window 
		wutil.switchwindow(d,"http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		
		// click on save button
		d.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		
		// to take screenshot of contact
//		wutil.screenshot(d, "contact");
		
		Thread.sleep(2000);
		
		//moushover on image 
		WebElement image = d.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mousehover(d, image);		
		
		//Click on Signout
		d.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		
		
	

		

	}
}
