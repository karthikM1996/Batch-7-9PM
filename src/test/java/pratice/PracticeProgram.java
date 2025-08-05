package pratice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeProgram {

	public static void main(String[] args) {
		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Login
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("k4spider");
		 WebElement target = driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys("10");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
        //validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
	    String msg = toastmsg.getText();
	
	    if (msg.contains("k4spider")) 
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
