package DDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ToReadTheDataFromPropertyfile {
 
	public static void main(String[] args) throws Throwable {
		 
		//create object of FileInputStream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		
		//object of property file
		Properties Prop=new Properties();
		
		//load all keys
		Prop.load(fis);
		
		//get properties
		String BROWSER = Prop.getProperty("Browser");
		String URL = Prop.getProperty("Url");
		String USERNAME = Prop.getProperty("Username");
		String PASSWORD = Prop.getProperty("Password");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		
		//actual script
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
		 
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.get(URL);
		 driver.findElement(By.id("username")).sendKeys(USERNAME);
		 driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		 driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		 
		

}
}
