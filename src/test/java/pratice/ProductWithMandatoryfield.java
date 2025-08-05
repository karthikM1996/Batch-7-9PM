package pratice;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductWithMandatoryfield {

	public static void main(String[] args) throws Throwable {
		
		//create object of FileInputStream
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
				Properties Prop=new Properties();
				Prop.load(fis);
				String BROWSER = Prop.getProperty("Browser");
				String URL = Prop.getProperty("Url");
				String USERNAME = Prop.getProperty("Username");
				String PASSWORD = Prop.getProperty("Password");
				
				
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
				 
		
		//Read data from excel
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScrpitData.xls.xlsx");
        Workbook wb = WorkbookFactory.create(fis1);
        Sheet sh = wb.getSheet("product");
         Row r = sh.getRow(1);
        String pname = r.getCell(0).getStringCellValue();
        String Qty = r.getCell(1).getStringCellValue();
        String price1 = r.getCell(2).getStringCellValue();
				 
			
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        Random ran=new Random();
        int randcount = ran.nextInt(10000);
        
        
		//Login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//adding product
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(pname+randcount);
		
		WebElement list = driver.findElement(By.name("productCategory"));
		Select s=new Select(list);
		s.selectByValue("Electronics");	
		
		WebElement quantityno = driver.findElement(By.name("quantity"));
		quantityno.clear();
		quantityno.sendKeys(Qty);
		
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(price1);
		
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select a=new Select(vendor);
		a.selectByValue("VID_001");
		
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		
		//validation
        WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
	    String msg = toastmsg.getText();
	    System.out.println(msg);
	
	    if (msg.contains("Successfully")) 
	    {
	    System.out.println("product is added");	
	    }
	    else
	   {
		System.out.println("product is not add");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	   
	   //logout
	    WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		
		driver.quit();
	
	}

}
