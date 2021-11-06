package utilities;

import java.util.Random;

import org.openqa.selenium.WebDriver;

public class Utilities {

	WebDriver driver;

	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	public static int randomNum() {
		Random rand = new Random();
		int no = rand.nextInt(1000);
		return no;
	}

}
