package stepdef;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.Scenario;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.ProductCartPage;
import pages.UserRegistrationPage;
import runner.BaseRunner;

public class StepDefinitionWeb extends BaseRunner {

	public WebDriver driver;
	Scenario scenario;
	final Logger log = Logger.getLogger(StepDefinitionWeb.class);
	File driverPath = new File("C:\\Users\\drivers");
	public HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	ProductCartPage productCartPage;

	@BeforeStep
	public void beforeStep(Scenario scenario) throws InterruptedException {
		this.scenario = scenario;
		Thread.sleep(2000);
	}

	@Given("^when user launches the browser$")
	public void when_user_launches_the_browser() {
		BasicConfigurator.configure();
		// Log4j logs in console output
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
		System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
		System.setProperty("webdriver.chrome.whitelistedIps", "");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
		log.info("User has opened the automated browser in maximize mode");
	}

	@Given("user loads the website")
	public void user_loads_the_website() {
		driver.get("https://www.takealot.com/");
		log.info("User loads the Takealot website in the browser");
	}

	@When("^user clicks on the Register link$")
	public void user_clicks_on_the_Register_link() {
		homePage = new HomePage(driver);
		homePage.clickRegistrationLink();
		log.info("User clicks on the Register link");
	}

	@When("user fills in the register form {string}, {string}, {string}, {string} and {string}")
	public void user_fills_in_the_register_form(String fname, String lname, String email, String password,
			String teleNo) {
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.fillRegistrationForm(fname, lname, email, password, teleNo);
		log.info("User fills in the registration form");
	}

	@Then("verify user is registered to the site {string}")
	public void verify_user_is_registered_to_the_site(String expectedMessage) throws Exception {
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.verifyRegistrationStatus(expectedMessage);
		log.info("User registered successfully to the Takealot website");
	}

	@When("user searches for the product {string}")
	public void user_searches_for_the_product(String product) {
		homePage = new HomePage(driver);
		homePage.globalSearchProducts(product);
	}

	@When("filter by specific brand {string} and colour")
	public void filter_by_specific_brand_and_colour(String brand) throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.filterSearchResults(brand);
	}

	@When("add specific watch {string} to the basket")
	public void add_specific_watch_to_the_basket(String model) throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.addSpecificProductToCart(model);
	}

	@Then("verify whether the product added correctly to the basket {string}")
	public void verify_whether_the_product_added_correctly_to_the_basket(String expectedProductInCart)
			throws InterruptedException {
		productCartPage = new ProductCartPage(driver);
		productCartPage.verifyProductAddedToCart(expectedProductInCart);
	}

	@Then("close the browser")
	public void close_the_browser() {
		driver.quit();
		log.info("Status of the test is: " + scenario.getStatus());
	}
}