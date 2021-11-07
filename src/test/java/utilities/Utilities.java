package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	
	public static void takeScreenshot(WebDriver driver, String path) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileUtils.copyFile(source, destination);
	}
}
