package testNGPratice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert1 {

	
	@Test
	public void demo()
	{
		String exptitle="Facebook – log in or sign u";
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");

		String acttitle = driver.getTitle();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(exptitle, acttitle);
		System.out.println("abcd");
		System.out.println("efgh");
		soft.assertAll();
	}
}
