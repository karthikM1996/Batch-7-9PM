package implemenationofTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.LoginPage;

public class CreateCampainWithExpectedDate extends BaseClass{

	@Test
	public void CreateCampaigWithExpectedDate() throws IOException {
		
		 PropertiesFileUtility putil=new PropertiesFileUtility();
		 ExcelFileUtility eutil=new ExcelFileUtility();
		 JavaUtility jutil=new JavaUtility();
		 WebDriverUtility wutil=new WebDriverUtility();
		 

		 String campname = eutil.toReadDataFromExcelFile("campaign", 1, 2);
         String target = eutil.toReadDataFromExcelFile("campaign", 1, 3); 

		
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
	
	
}
}


	


