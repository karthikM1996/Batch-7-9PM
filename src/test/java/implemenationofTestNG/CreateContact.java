package implemenationofTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Contactpage;
import objectRepositories.Homepage;
import objectRepositories.LoginPage;

public class CreateContact extends BaseClass{

	@Test
	public void CreateContace() throws Exception {
		
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		
		String campname = eutil.toReadDataFromExcelFile("campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("campaign", 1,3);
		String Orgname = eutil.toReadDataFromExcelFile("contact", 1, 0);
		String  title1= eutil.toReadDataFromExcelFile("contact", 1, 1);
		String department = eutil.toReadDataFromExcelFile("contact", 1, 2);
		String officephone = eutil.toReadDataFromExcelFile("contact", 1, 3);
		String contactname = eutil.toReadDataFromExcelFile("contact", 1, 4);
		String mobileno = eutil.toReadDataFromExcelFile("contact", 1, 5);
		String email = eutil.toReadDataFromExcelFile("contact", 1, 6);
		
	 
		String daterequire = jutil.togetRequiredDate(30);
		
		//create campaign
		Homepage hp=new Homepage(driver);
		hp.getCreatecampaign().click();
		
		Campaignpage cp=new Campaignpage(driver);
		cp.getCampaignnameTF().sendKeys(campname);
		cp.getCampaignstatusTF().sendKeys("pass");
		WebElement target1 = cp.getTargetsizeTF();
		target1.clear();
		target1.sendKeys(target);
		WebElement expclosedate = cp.getDateTF();
		wutil.passInput(driver, expclosedate, daterequire);
		cp.getCreatCampSubmitBTN().click();

		
        //validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibiliyOfElement(driver, toastmsg);
	    String msg = toastmsg.getText();
	   
	    
	
	    if (msg.contains(campname)) 
	    {
	    System.out.println("campaign is created ");	
	    }
	    else
	   {
		System.out.println("campaign is not created ");
	   }
	    cp.getClosemsg().click();
	   
	   //Create Contact
	   hp.getContact().click();
	   Contactpage cont=new Contactpage(driver);
	   cont.getCreatecontactBTN().click();
	   cont.getOrgnameTF().sendKeys(Orgname);
	   cont.getTitleTF().sendKeys(title1);
	   cont.getDeptnameTF().sendKeys(department);
	   cont.getOfficephoneTF().sendKeys(officephone);
	   cont.getContactnameTF().sendKeys(contactname);
	   cont.getMobilenoTF().sendKeys(mobileno);
	   cont.getEmailTF().sendKeys(email);
	   cont.getCampaignaddbtn().click();
	  
	   //Handling window tab
	   String mainwindow = driver.getWindowHandle();
	   
	   wutil.SwitchToWindow(driver);
	   
	 
	  // to select the campaignName in dropdown
	  WebElement select = cont.getCampaignIDDrop();
	  wutil.select(select, "campaignName");

	 
	   //enter keys in searchbox
	  cont.getSearchTF().sendKeys(campname);
	  cont.getSelectBTN().click();
	  
	 //switching control back to mainwindow
	driver.switchTo().window(mainwindow);
			

	}

	
	}

