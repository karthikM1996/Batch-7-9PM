package testNGPratice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.logging.FileHandler;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Screenshot {

	
	@Test
	public void takescreenshot() throws Exception
	{
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.facebook.com/");
		Date d=new Date();
		String newdate = d.toString().replace(" ", "_").replace(":","_");

		String title = driver.getTitle();
		TakesScreenshot shot=(TakesScreenshot) driver;
		File scr = shot.getScreenshotAs(OutputType.FILE);
		File drc=new File("./screenshot/"+title+""+newdate+".png");
		org.openqa.selenium.io.FileHandler.copy(scr, drc);
		
	}
}
