package implementationOfUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.LoginPage;

public class CreateCampainWithExpectedDate {

	public static void main(String[] args) throws Throwable {
		
		 PropertiesFileUtility putil=new PropertiesFileUtility();
		 ExcelFileUtility eutil=new ExcelFileUtility();
		 JavaUtility jutil=new JavaUtility();
		 WebDriverUtility wutil=new WebDriverUtility();
		 
		 String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		 String URL = putil.togetDataFromPropertiesFile("Url");
		 String USERNAME = putil.togetDataFromPropertiesFile("Username");		
		 String PASSWORD = putil.togetDataFromPropertiesFile("Password");
		
		
		WebDriver driver=null;
		
		 if(BROWSER.equals("Edge"))
		 {
			 driver=new EdgeDriver();
		 }
		 else if(BROWSER.equals("Chrome"))
		 {
			 driver=new ChromeDriver();
			
		 }
		 else if(BROWSER.equals("Firefox"))
		 {
			  driver=new FirefoxDriver();
		 }
		 
		
		 String campname = eutil.toReadDataFromExcelFile("campaign", 1, 2);
         String target = eutil.toReadDataFromExcelFile("campaign", 1, 3); 

     
		
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		
		//Login
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
	    lp.getUN().sendKeys(USERNAME);
	    lp.getPW().sendKeys(PASSWORD);
	    lp.getLoginBtn().click();
		
		
		
		//date after 30 days
		String daterequire = jutil.togetRequiredDate(30);
		
		
		
		//create campaign
		Homepage hp=new Homepage(driver);
	    hp.getCreatecampaign().click();
		
		Campaignpage cp=new Campaignpage(driver);
		cp.getCampaignnameTF().sendKeys(campname);
		cp.getCampaignstatusTF().sendKeys("pass");
		WebElement size = cp.getTargetsizeTF();
		size.clear();
		size.sendKeys(target);
		
		WebElement expclosedate = cp.getDateTF();
		wutil.passInput(driver, expclosedate, daterequire);
	
		
		
		cp.getCreatCampSubmitBTN().click();
		
		
		
        //validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibiliyOfElement(driver, toastmsg);
	    String msg = toastmsg.getText();
	
	    if (msg.contains(campname)) 
	    {
	    System.out.println("campaign is created");
	    }
	    else
	   {
		System.out.println("campaign is not created");
	   }
	    cp.getClosemsg().click();
	
	//Logout
	WebElement icon = hp.getUserIcon();
	
	wutil.mouseOverOnWeblement(driver, icon);
	WebElement logout = hp.getLogoutbtn();
	wutil.clickOnWebElement(driver, logout);
	driver.quit();
			
	

	
	
}
}


	


