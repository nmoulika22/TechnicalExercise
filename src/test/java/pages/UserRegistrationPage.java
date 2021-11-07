package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.Assert;
import utilities.Utilities;

public class UserRegistrationPage {

	WebDriver driver;

	public UserRegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void fillRegistrationForm(String fname, String lname, String email, String password, String teleNo) {
		WebElement registrationLink = driver.findElement(By.linkText("Register"));
		registrationLink.click();
		WebElement firstName = driver.findElement(By.id("firstname"));
		firstName.sendKeys(fname);
		WebElement surName = driver.findElement(By.id("surname"));
		surName.sendKeys(lname);
		int randomNo = Utilities.randomNum();
		WebElement emailId = driver.findElement(By.id("email"));
		emailId.sendKeys(email + randomNo + "@gmail.com");
		WebElement confirmEmailId = driver.findElement(By.id("email2"));
		confirmEmailId.sendKeys(email + randomNo + "@gmail.com");
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(password);
		WebElement confirmPasswordField = driver.findElement(By.id("password2"));
		confirmPasswordField.sendKeys(password);
		WebElement telephoneNumber = driver.findElement(By.id("telno1"));
		telephoneNumber.sendKeys(teleNo);
		WebElement electronicsCheckbox = driver.findElement(By.id("newsletter_electronics"));
		electronicsCheckbox.click();
		WebElement registrationButton = driver.findElement(By.name("registerButton"));
		registrationButton.click();
	}

	public void verifyRegistrationStatus(String expectedMessage) throws Exception {
		try {
			String actualMessage = driver.findElement(By.cssSelector("div[class^='fancybox-wrap'] h3")).getText()
					.trim();
			Assert.assertEquals(expectedMessage, actualMessage);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

}
