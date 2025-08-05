package genericUtility;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.logging.FileHandler;

import javax.swing.text.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v133.filesystem.model.File;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	
	public void waitForPageToLoad(WebDriver driver)
	
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForVisibiliyOfElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void SwitchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void SwitchToFrame(WebDriver driver,String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
	
	public void SwitchToFrame(WebDriver driver,WebElement frameEle)
	{
		driver.switchTo().frame(frameEle);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void switchToAlertAndSendkeys(WebDriver driver,String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	public String switchToAlertAndGetText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void select(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	public void select(String text,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void mouseOverOnWeblement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void clickOnWebElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	
	public void doubleClickOnWebElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	
	}
	public void rightClickOnWebElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	public void passInput(WebDriver driver,WebElement element,String text)
	{
		Actions act=new Actions(driver);
	    act.click(element).sendKeys(text).perform();
	}
	 public void SwitchToWindow(WebDriver driver)
	 {
		 Set<String> allWinId = driver.getWindowHandles();
		 for (String id : allWinId)
		 {
			driver.switchTo().window(id);
		}
	 }
	 
	 public void takeScreenShot(WebDriver driver,String fileName) throws IOException
	 {
		 TakesScreenshot ts=(TakesScreenshot) driver;
		java.io.File temp = ts.getScreenshotAs(OutputType.FILE);
		java.io.File perm=new java.io.File("./errorshots/"+fileName+".png");
		org.openqa.selenium.io.FileHandler.copy(temp, perm);
		
	 }
	 
	 public void toscrollBy(WebDriver driver,int x,int y)
	 {
		 JavascriptExecutor js=(JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy("+x+","+y+")");
	 }
	 
	
}
