package Product;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.Productpage;

public class ProductTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void ProductWithMandatoryfieldTest() throws IOException {
		
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
