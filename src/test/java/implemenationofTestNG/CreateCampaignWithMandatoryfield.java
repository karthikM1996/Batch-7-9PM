package implemenationofTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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

public class CreateCampaignWithMandatoryfield extends BaseClass{

@Test
public void CreateCampaginWithMandatoryfield() throws Exception {
		
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//Read data from excel
		String campname = eutil.toReadDataFromExcelFile("campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("campaign", 1, 3);
         
	//create campaign
    Homepage hp=new Homepage(driver);
    hp.getCreatecampaign().click();
	
    Campaignpage cp=new Campaignpage(driver);
    cp.getCampaignnameTF().sendKeys(campname);
    WebElement size = cp.getTargetsizeTF();
    size.clear();
    size.sendKeys(target);
    Thread.sleep(1000);
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
