package pages;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stepdef.StepDefinitionWeb;

public class HomePage extends StepDefinitionWeb {

	WebDriver driver;
	static Logger log = Logger.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegistrationLink() {
		WebElement registrationLink = driver.findElement(By.linkText("Register"));
		registrationLink.click();
	}

	public void globalSearchProducts(String product) {
		WebElement globalSearchInputField = driver.findElement(By.cssSelector("input.search-field"));
		globalSearchInputField.sendKeys(product);
		WebElement globalSearchButton = driver.findElement(By.cssSelector("button[type='submit']"));
		globalSearchButton.click();
	}

	public void filterSearchResults(String brand) {
		WebElement brandInputField = driver.findElement(By.cssSelector("input[placeholder='Search by Brand']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandInputField);
		brandInputField.sendKeys(brand);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		WebElement brandCheckbox = driver.findElement(By.cssSelector("label[for='filter_Garmin_Garmin']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandCheckbox);
		brandCheckbox.click();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		WebElement colourCheckbox = driver.findElement(By.cssSelector("label[for*='BasicColours_Black']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", colourCheckbox);
		colourCheckbox.click();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}

	public void addSpecificProductToCart(String model) throws InterruptedException {
		WebElement specificModel = driver.findElement(
				By.xpath("//a[contains(@href,'" + model + "')]//parent::div//button[@data-ref='add-to-cart-button']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", specificModel);
		Thread.sleep(1000);
		specificModel.click();
	}
}