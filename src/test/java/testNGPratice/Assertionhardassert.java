package testNGPratice;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertionhardassert {

	@Test
	public void demo() {
		
		String exptitle="Facebook – log in or sign up";
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");

		String acttitle = driver.getTitle();
		Assert.assertEquals(exptitle, acttitle);
		System.out.println("abcds");
	}
}
