package implemenationofTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
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
import objectRepositories.Productpage;

public class ProductWithMandatoryfield extends BaseClass{

	@Test
	public void ProductWithMandatoryfield() throws IOException {
		
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//Read data from excel
		String pname = eutil.toReadDataFromExcelFile("product", 1, 0);
		String Qty = eutil.toReadDataFromExcelFile("product", 1, 1);
		String price1 = eutil.toReadDataFromExcelFile("product", 1, 2);
				
        jutil.togetRandomNumer();
        
		//adding product
		Homepage hp=new Homepage(driver);
		hp.getProduct().click();
		
		Productpage pg=new Productpage(driver);
		pg.getAddBtn().click();
		pg.getPnameTF().sendKeys(pname+jutil.togetRandomNumer());
		
		WebElement list = pg.getProcat();
		wutil.select(list, "Electronics");
		
		
		WebElement quantityno = pg.getQtyTF();
		quantityno.clear();
		quantityno.sendKeys(Qty);
		
		WebElement price = pg.getPrice1TF();
		price.clear();
		price.sendKeys(price1);
		
		WebElement vendor = pg.getVendId();
		wutil.select(vendor, "VID_001");
		
		pg.getSubmitAddbtn().click();
		
		//validation
		Campaignpage cp=new Campaignpage(driver);
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibiliyOfElement(driver, toastmsg);
	    String msg = toastmsg.getText();

	
	    if (msg.contains("Successfully")) 
	    {
	    System.out.println("product is added");	
	    }
	    else
	   {
		System.out.println("product is not add");
	   }
	    cp.getClosemsg().click();
	    
	    
	   
	
	}

}
