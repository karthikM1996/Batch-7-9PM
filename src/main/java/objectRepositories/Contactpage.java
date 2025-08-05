package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contactpage {

	
	
	WebDriver driver;
	public Contactpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Create Contact']")
	private WebElement createcontactBTN;
	
	@FindBy(name = "organizationName")
	private WebElement orgnameTF;
	
	@FindBy(name = "title")
	private WebElement titleTF;
	

	@FindBy(name = "department")
	private WebElement deptnameTF;
	
	@FindBy(name = "officePhone")
	private WebElement officephoneTF;
	
	@FindBy(name = "contactName")
	private WebElement contactnameTF;
	
	@FindBy(name = "mobile")
	private WebElement mobilenoTF;
	
	@FindBy(name = "email")
	private WebElement emailTF;
	
	
	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement campaignaddbtn;
	
	@FindBy(xpath = "//select[@id='search-criteria']")
	private WebElement campaignIDDrop;
	
	@FindBy(xpath = "//input[@id='search-input']")
	private WebElement searchTF;
	
	
	@FindBy(className = "select-btn")
	private WebElement selectBTN;


	public WebElement getCreatecontactBTN() {
		return createcontactBTN;
	}


	public WebElement getOrgnameTF() {
		return orgnameTF;
	}


	public WebElement getTitleTF() {
		return titleTF;
	}


	public WebElement getDeptnameTF() {
		return deptnameTF;
	}


	public WebElement getOfficephoneTF() {
		return officephoneTF;
	}


	public WebElement getContactnameTF() {
		return contactnameTF;
	}


	public WebElement getMobilenoTF() {
		return mobilenoTF;
	}


	public WebElement getEmailTF() {
		return emailTF;
	}


	public WebElement getCampaignaddbtn() {
		return campaignaddbtn;
	}


	public WebElement getCampaignIDDrop() {
		return campaignIDDrop;
	}


	public WebElement getSearchTF() {
		return searchTF;
	}


	public WebElement getSelectBTN() {
		return selectBTN;
	}
	
	
	
	
	
	
	
	
}
