package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class ProductCartPage {

	WebDriver driver;

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyProductAddedToCart(String expectedProductInCart) throws InterruptedException {
		Thread.sleep(10000);
		int expectedQuantityInCart = 1;
		WebElement cartItemCount = driver.findElement(By.cssSelector("div.badge-count"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartItemCount);
		Thread.sleep(1000);
		Assert.assertEquals(expectedQuantityInCart, Integer.parseInt(cartItemCount.getText()));
		WebElement cartButton = driver.findElement(By.cssSelector("button[class*='mini-cart-button']"));
		cartButton.click();
		Thread.sleep(10000);
		System.out.println(driver.getTitle());
		WebElement productInCartPage = driver.findElement(By.cssSelector("a h3"));
		System.out.println(productInCartPage.getText());
		Assert.assertEquals(expectedProductInCart, productInCartPage.getText());
	}
}
