package pratice;

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

public class CreateContact {

	public static void main(String[] args) throws IOException, Exception {
		
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		WebDriver driver=null;
		if(BROWSER.contains("Edge"))
		{
			 driver=new EdgeDriver();
		}
		if(BROWSER.contains("Chrome"))
		{
			 driver=new ChromeDriver();
		}
		if(BROWSER.contains("Firefox"))
		{
			 driver=new FirefoxDriver();
		}
		
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestScrpitData.xls.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("campaign");
		Row row = sh.getRow(1);
		String campname = row.getCell(2).getStringCellValue();
		String target = row.getCell(3).getStringCellValue();
		Sheet sh1 = wb.getSheet("contact");
		Row row1 = sh1.getRow(1);
		String Orgname = row1.getCell(0).getStringCellValue();
		String title1 = row1.getCell(1).getStringCellValue();
		String department = row1.getCell(2).getStringCellValue();
		String officephone = row1.getCell(3).getStringCellValue();
		String contactname = row1.getCell(4).getStringCellValue();
		String mobileno = row1.getCell(5).getStringCellValue();
		String email = row1.getCell(6).getStringCellValue();
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	 
		Random ran=new Random();
		int randcount = ran.nextInt(2000);
		
		//date after 30 days
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String daterequire = sim.format(cal.getTime());
		
		//create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname+randcount);
		driver.findElement(By.name("campaignStatus")).sendKeys("pass");
		
		 WebElement target1 = driver.findElement(By.name("targetSize"));
		target1.clear();
		target1.sendKeys(target);
		
		WebElement expclosedate = driver.findElement(By.name("expectedCloseDate"));
		Actions act=new Actions(driver);
		act.click(expclosedate).sendKeys(daterequire).perform();
		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		
        //validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
	    String msg = toastmsg.getText();
	   
	    
	    Thread.sleep(2000);
	
	    if (msg.contains(campname)) 
	    {
	    System.out.println("campaign is created");	
	    }
	    else
	   {
		System.out.println("campaign is not created");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	   
	   //Create Contact
	   driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	   driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
	   driver.findElement(By.name("organizationName")).sendKeys(Orgname);
	   driver.findElement(By.name("title")).sendKeys(title1);
	   driver.findElement(By.name("department")).sendKeys(department);
	   driver.findElement(By.name("officePhone")).sendKeys(officephone);
	   driver.findElement(By.name("contactName")).sendKeys(contactname);
	   driver.findElement(By.name("mobile")).sendKeys(mobileno);
	   driver.findElement(By.name("email")).sendKeys(email);
	   driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	  
	   //Handling window tab
	   String mainwindow = driver.getWindowHandle();
	  Set<String> allwindow = driver.getWindowHandles();
	  for (String lv: allwindow)
     {
		  driver.switchTo().window(lv);
		
	 }
	 
	  // to select the campaignName in dropdown
	  WebElement select = driver.findElement(By.id("search-criteria"));
	  Select sel=new Select(select);
	   sel.selectByValue("campaignName");
	   
	   //enter keys in searchbox
	   driver.findElement(By.id("search-input")).sendKeys(campname); 
	   driver.findElement(By.className("select-btn")).click();
	
	 //switching control back to mainwindow
	driver.switchTo().window(mainwindow);
	
	//Logout
	WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	Actions act1=new Actions(driver);
	act1.moveToElement(icon).perform();
	WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
	act1.moveToElement(logout).click().perform();

	}

	
	}

