package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Productpage {

	WebDriver driver;
	public Productpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Add Product']")	
	private WebElement addBtn;
	
	@FindBy(name = "productName")	
	private WebElement pnameTF;
	
	@FindBy(name = "quantity")	
	private WebElement QtyTF;
	
	@FindBy(name = "price")	
	private WebElement price1TF;
	
	@FindBy(name = "productCategory")	
	private WebElement procat;
	
	@FindBy(name = "vendorId")	
	private WebElement vendId;
	

	@FindBy(xpath = "//button[text()='Add']")	
	private WebElement submitAddbtn;
	
	
	


	public WebElement getSubmitAddbtn() {
		return submitAddbtn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getProcat() {
		return procat;
	}

	public WebElement getVendId() {
		return vendId;
	}

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getPnameTF() {
		return pnameTF;
	}

	public WebElement getQtyTF() {
		return QtyTF;
	}

	public WebElement getPrice1TF() {
		return price1TF;
	}
	
	
		
		
	}

