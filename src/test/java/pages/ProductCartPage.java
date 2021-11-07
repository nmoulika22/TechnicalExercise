package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.Assert;
import utilities.Utilities;

public class ProductCartPage {

	WebDriver driver;

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyProductAddedToCart(String expectedProductInCart) throws InterruptedException, IOException {
		Thread.sleep(5000);
		int expectedQuantityInCart = 1;
		WebElement cartItemCount = driver.findElement(By.cssSelector("div.badge-count"));
		Thread.sleep(1000);
		Assert.assertEquals(expectedQuantityInCart, Integer.parseInt(cartItemCount.getText()));
		System.out.println("Actual cart quantity: "+cartItemCount.getText());
		WebElement cartButton = driver.findElement(By.cssSelector("span.cart-icon"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", cartButton);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(1000);
		WebElement cartIcon = driver.findElement(By.cssSelector("button[class='button cart pay']"));
		if (cartIcon.isDisplayed()) {
			cartIcon.click();
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		}
		Utilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\screenshots\\cartpage.png");
		System.out.println(driver.getTitle());
		WebElement productInCartPage = driver.findElement(By.cssSelector("a h3"));
		System.out.println(productInCartPage.getText().trim());
		Assert.assertEquals(expectedProductInCart, productInCartPage.getText());
	}
}