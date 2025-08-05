package BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepositories.Homepage;
import objectRepositories.LoginPage;

public class BaseClass {

	public WebDriver driver=null;
	public static WebDriver sdriver=null;//listeners
	public PropertiesFileUtility putil=new PropertiesFileUtility();
	public WebDriverUtility wutil=new WebDriverUtility();
	
	@BeforeSuite(groups = {"smoke","regression"})
	public void BeforeSuite()
	{
		System.out.println("DB connectivity open");
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smoke","regression"})
	public void BeforeClass() throws IOException
	{
		//String BROWSER=browser;
		
		String BROWSER = putil.togetDataFromPropertiesFile("Browser");
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
		sdriver=driver;
		
		System.out.println("launching Browser");
		 
	}
	
	@BeforeMethod(groups = {"smoke","regression"})
	public void BeforeMethod() throws IOException
	{
		
		String URL = putil.togetDataFromPropertiesFile("Url");
		String USERNAME = putil.togetDataFromPropertiesFile("Username");
		String PASSWORD = putil.togetDataFromPropertiesFile("Password");
		
		driver.get(URL);
	    LoginPage lp=new LoginPage(driver);
	    lp.getUN().sendKeys(USERNAME);
	    lp.getPW().sendKeys(PASSWORD);
	    lp.getLoginBtn().click();
	    driver.manage().window().maximize();
        wutil.waitForPageToLoad(driver);
		
	    System.out.println("login");
		
	}
	
	@AfterMethod(groups = {"smoke","regression"})
	public void AfterMethod()
	{
		Homepage hp=new Homepage(driver);
		WebElement icon = hp.getUserIcon();
		  wutil.mouseOverOnWeblement(driver, icon);
		  WebElement logout = hp.getLogoutbtn();
		  wutil.clickOnWebElement(driver, logout);
		  
		  System.out.println("logout");

	}
	
	@AfterClass(groups = {"smoke","regression"})
	public void AfterClass()
	{
		driver.quit();
		System.out.println("closing Browser");
	}
	 @AfterSuite(groups = {"smoke","regression"})
	 public void AfterSuite()
	 {
		 System.out.println("DB connectivity close");
	 }
}
