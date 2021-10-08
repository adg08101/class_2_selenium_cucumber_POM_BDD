package selenium_cucumber.selenium_cucumber.goheavy.account.page;

//import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

public class AccountPage extends PageObject {
	private String formScroll = "//*[@id=\"account-settings\"]/ancestor::div[@class=\"templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj\"]";

	public AccountPage() {
		super();
		this.urlpath = "accountsettings";
	}

	public WebElement getFrom() {
		return this.getWebElement(By.cssSelector("#account-settings"));
	}

	public void getFromElements() {
		waitForSpinningElementDissapear();
		
		By avatar_element = By.xpath("//input[@type='file']");
		getWait().until(ExpectedConditions.presenceOfElementLocated(avatar_element));
		
		//HashMap<String, WebElement> 
		//Setting avatar
		
		WebElement photo = this.getWebElement(avatar_element);
		String url = (String) Setup.getValueStore("avatar");
		photo.sendKeys(url);

		// Scrolling the page to get the element activated
		this.scroll(formScroll, By.id("addressStateId"));
		
		waitForSpinningElementDissapear();

		// Getting State
		By state_element = By.id("addressStateId");
		getWait().until(ExpectedConditions.elementToBeClickable(state_element));
		
		WebElement stateInput = this.getWebElement(state_element);
		Setup.getActions().moveToElement(stateInput).click().perform();
		
		By state_list_elements = By.xpath("//div[@id='addressStateId_list']/ancestor::div[contains(@class,'ant-select-dropdown')]/descendant::div[contains(@class,'ant-select-item ant-select-item-option')]/span");
		getWait().until(ExpectedConditions.presenceOfElementLocated(state_list_elements));
	
		List<WebElement> addressStateId_list = this.getWebElements(state_list_elements);
		
		WebElement addr = addressStateId_list.get(new Random().nextInt(addressStateId_list.size()));
		Setup.getActions().moveToElement(addr).click().perform();
	}

	public WebElement getUpdateButton() {
		return this.getWebElement(By.xpath("//*[@id=\"account-settings\"]//button"));
	}

	public WebElement getPopupMessage() {
		Setup.getWait().visibilityOfElement(By.xpath("//div[@class='ant-notification-notice-message']"),
				"Not element message");
		return this.getWebElement(By.xpath("//div[@class='ant-notification-notice-message']"));
	}

	public boolean clear_mandatory_field(String field) throws Exception {
		waitForSpinningElementDissapear();
		// TODO Auto-generated method stub
		if (field.equals("first name")) {
			waitForSpinningElementDissapear();
			Setup.getWait().waitForLoading(10000);
			By name = By.id("firstName");
			getWait().until(ExpectedConditions.elementToBeClickable(name));
			getWebElement(name).clear();
			return true;
		}
		return false;
	}

	public WebElement get_required_field_message() {
		waitForSpinningElementDissapear();
		// TODO Auto-generated method stub
		By alert = By.xpath("//div[@role='alert']");
		getWait().until(ExpectedConditions.presenceOfElementLocated(alert));
		return getWebElement(alert);
	}
}
