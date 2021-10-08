package selenium_cucumber.selenium_cucumber.general;

//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingObject {

	private WebDriver driver;

	public WaitingObject(WebDriver driver) {
		this.driver = driver;
		this.waitForLoading(20);
	}

	/**
	 * @executeExpectedCondition Method to execute the wait statement
	 * @param expected
	 * @param message
	 * @param time
	 */
	public void executeExpectedCondition(ExpectedCondition<?> expected, String message, int time) {
		waitMethod(10).withMessage(message).until(expected);
	}

	/**
	 *
	 * @param time
	 */
	public void setImplicityWait(int time) {

	}

	/**
	 *
	 * @param time
	 */
	public void waitForLoading(long time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	/**
	 *
	 * @param time
	 */
	public void implicityWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 *
	 * @param by
	 * @param time
	 */
	public void waitUntilElementAppear(By by, int time) {
		WebElement element1 = driver.findElement(by);
		ExpectedCondition<?> expectedCondition = ExpectedConditions.visibilityOf(element1);
		String mss = "Element " + element1 + " not found";
		executeExpectedCondition(expectedCondition, mss, time);

	}

	/**
	 *
	 * @param by
	 * @param time
	 */
	public void waitUntilElementDisappear(By by, int time) {
		WebElement element1 = null;
		try {
			element1 = driver.findElement(by);
		} catch (Exception e) {
			return;
		}
		ExpectedCondition<?> expectedCondition = ExpectedConditions.invisibilityOf(element1);
		String mss = "Element " + element1 + " still in dom ";
		executeExpectedCondition(expectedCondition, mss, time);

	}

	/**
	 *
	 * @param time
	 * @return
	 */
	private WebDriverWait waitMethod(long time) {
		return new WebDriverWait(this.driver, time);
	}

	/**
	 *
	 * @param by
	 * @param msg
	 * @return
	 */
	public WebElement visibilityOfElement(By by, String msg) {
		WebDriverWait wait = waitMethod(10);
		if (!msg.equals("")) {
			wait.withMessage(msg);
		}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 *
	 * @param expectedMssg
	 */
	public void textAppear(String expectedMssg) {
		visibilityOfElement(By.xpath("//*[contains(text(),'" + expectedMssg + "')]"),
				"Unable to locate text '" + expectedMssg + "'");

	}

	/**
	 *
	 * @param time
	 */
	public void thread(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param save
	 */
	public void waiForElementClick(WebElement save) {
		waitMethod(10).until(ExpectedConditions.elementToBeClickable(save)).click();
	}
}
