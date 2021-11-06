package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class ProductCartPage {

	WebDriver driver;

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyProductAddedToCart(String expectedProductInCart) {
		int expectedQuantityInCart = 1;
		WebElement cartItemCount = driver.findElement(By.cssSelector("div.badge-count"));
		Assert.assertEquals(expectedQuantityInCart, Integer.parseInt(cartItemCount.getText()));
		WebElement cartButton = driver.findElement(By.cssSelector("button[class*='mini-cart-button']"));
		cartButton.click();
		WebElement productInCartPage = driver.findElement(By.cssSelector("h3[class*='cart-item-module_item-title']"));
		Assert.assertEquals(expectedProductInCart, productInCartPage.getText());
	}
}
