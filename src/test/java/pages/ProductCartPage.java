package pages;

import java.io.IOException;
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
		Thread.sleep(10000);
		int expectedQuantityInCart = 1;
		WebElement cartItemCount = driver.findElement(By.cssSelector("div.badge-count"));
		Thread.sleep(1000);
		Assert.assertEquals(expectedQuantityInCart, Integer.parseInt(cartItemCount.getText()));
		System.out.println("Actual cart quantity: "+cartItemCount.getText());
		WebElement cartButton = driver.findElement(By.cssSelector("span.cart-icon"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", cartButton);
		Thread.sleep(20000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(3000);
		Utilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\screenshots\\cartpage.png");
		System.out.println(driver.getTitle());
		WebElement productInCartPage = driver.findElement(By.cssSelector("a h3"));
		System.out.println(productInCartPage.getText().trim());
		Assert.assertEquals(expectedProductInCart, productInCartPage.getText());
	}
}
