package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
     
	WebDriver driver;
	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Campaigns']")
	private WebElement campaign;
	
	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contact;
	
	@FindBy(xpath = "//a[text()='Products']")
	private WebElement product;
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement createcampaign;
	
	@FindBy(xpath = "//div[@class='user-icon']")
	private WebElement userIcon;
	
	@FindBy(xpath = "//div[@class='dropdown-item logout']")
	private WebElement logoutbtn;

	public WebElement getCampaign() {
		return campaign;
	}

	public WebElement getContact() {
		return contact;
	}

	public WebElement getProduct() {
		return product;
	}

	public WebElement getCreatecampaign() {
		return createcampaign;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutbtn() {
		return logoutbtn;
	}
	
	
	
	
}
