package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Campaignpage {
     
	WebDriver driver;
	public Campaignpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignName")
	private WebElement campaignnameTF;
	
	@FindBy(name = "campaignStatus")
	private WebElement campaignstatusTF;
	
	@FindBy(name = "targetSize")
	private WebElement targetsizeTF;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement dateTF;
	
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement creatCampSubmitBTN;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closemsg;

	public WebElement getCampaignnameTF() {
		return campaignnameTF;
	}

	public WebElement getCampaignstatusTF() {
		return campaignstatusTF;
	}

	public WebElement getTargetsizeTF() {
		return targetsizeTF;
	}

	public WebElement getDateTF() {
		return dateTF;
	}

	public WebElement getCreatCampSubmitBTN() {
		return creatCampSubmitBTN;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}
	
	
	
	
	
}
