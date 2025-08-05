package Campaign;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;

@Listeners(ListenerUtility.ListenersImplementation.class)
public class CampaignTest extends BaseClass {

	
	@Test(groups = "smoke")
	public void CreateCampaignWithMandatoryfieldTest() throws Exception {
			
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
        Assert.assertTrue(msg.contains(campname));
	   cp.getClosemsg().click();

	}

	@Test(groups = "regression")
	public void CreateCampaignWithExpectedDateTest() throws IOException {
		
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
	    Assert.assertTrue(msg.contains(campname));
	    cp.getClosemsg().click();
	
	
      }

	@Test(groups = "smoke")
	public void CreateCampaignWithStatusTest() throws IOException {
	
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
		cp.getCampaignstatusTF().sendKeys("pass");
		WebElement size = cp.getTargetsizeTF();
		size.clear();
		size.sendKeys(target);
		cp.getCreatCampSubmitBTN().click();
		
        //validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibiliyOfElement(driver, toastmsg);
	    String msg = toastmsg.getText();
	    Assert.assertTrue(msg.contains(campname));
	    cp.getClosemsg().click();

	
}

}
