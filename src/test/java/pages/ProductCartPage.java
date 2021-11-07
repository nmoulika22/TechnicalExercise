package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.Assert;
import utilities.Utilities;

public class ProductCartPage {

	WebDriver driver;
	static Logger log = Logger.getLogger(ProductCartPage.class.getName());

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyProductAddedToCart(String expectedProductInCart) throws InterruptedException, IOException {
		Thread.sleep(5000);
		int expectedQuantityInCart = 1;
		WebElement cartItemCount = driver.findElement(By.cssSelector("div.badge-count"));
		Thread.sleep(1000);
		Assert.assertEquals(expectedQuantityInCart, Integer.parseInt(cartItemCount.getText()));
		log.info("Actual cart quantity: "+cartItemCount.getText());
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
		log.info(driver.getTitle());
		Assert.assertEquals("Cart - TAKEALOT", driver.getTitle().trim());
		WebElement productInCartPage = driver.findElement(By.cssSelector("a h3"));
		log.info(productInCartPage.getText().trim());
		Assert.assertEquals(expectedProductInCart, productInCartPage.getText());
	}
}