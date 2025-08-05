package pratice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateCampaignwithStatus {
 public static void main(String[] args) throws Throwable, IOException {
	
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
	        Sheet sh = wb.getSheet("campaign");
	        Row r = sh.getRow(1);
	        String campname = r.getCell(2).getStringCellValue();
	        String target = r.getCell(3).getStringCellValue();
	        
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        
		//Login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		
		Random ran=new Random();
		int randcount = ran.nextInt(1000);
		
		//create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname+randcount);
		driver.findElement(By.name("campaignStatus")).sendKeys("pass");
		WebElement target1 = driver.findElement(By.name("targetSize"));
		target1.clear();
		target1.sendKeys( target);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		
		
        //validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
	    String msg = toastmsg.getText();
	
	    if (msg.contains(campname)) 
	    {
	    System.out.println("campaign is created");	
	    }
	    else
	   {
		System.out.println("campaign is not created");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	
	//Logout
	WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	Actions act=new Actions(driver);
	act.moveToElement(icon).perform();
	WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
	act.moveToElement(logout).click().perform();
	
	driver.quit();
			
	

	
	
}
}
