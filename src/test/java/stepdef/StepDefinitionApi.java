package stepdef;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import junit.framework.Assert;
import cucumber.api.Scenario;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitionApi {

	private static final String API_KEY = "2911f66f0267c47b0755a56cacf64452";
	Scenario scenario;
	private static final String BASE_URL = "api.openweathermap.org/data/2.5/weather?";
	private static Response response;
	final Logger log = Logger.getLogger(StepDefinitionApi.class);

	@BeforeStep
	public void beforeStep(Scenario scenario) throws InterruptedException {
		this.scenario = scenario;
		Thread.sleep(2000);
	}

	@When("i hit the api end point with parameter type {string} and value {string}")
	public void i_hit_the_api_end_point_with_parameter(String param, String value) {
		BasicConfigurator.configure();
		// Log4j logs in console output
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
		RestAssured.baseURI = "http://" + BASE_URL + param + "=" + value + "&appid=" + API_KEY;
		log.info("API URL: "+BASE_URL + param + "=" + value + "&appid=" + API_KEY);
		RequestSpecification rs = RestAssured.given();
		try {
			response = rs.request(Method.GET, RestAssured.baseURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("i hit the api end point with parameter latitude {string} and longitude {string}")
	public void i_hit_the_api_end_point_with_parameter_latitude_and_longitude(String lat, String lon) {
		BasicConfigurator.configure();
		// Log4j logs in console output
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
		RestAssured.baseURI = "http://" + BASE_URL + "lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;
		log.info("API URL: "+BASE_URL + "lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY);
		RequestSpecification rs = RestAssured.given();
		try {
			response = rs.request(Method.GET, RestAssured.baseURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("verify the response is successful {string}, {string} and {string}")
	public void verify_the_response_is_successful(String code, String country, String name) {
		Assert.assertEquals(200, Integer.parseInt(code));
		log.info(response.asString());
		Assert.assertEquals(response.asString().contains(country), true);
		Assert.assertEquals(response.asString().toLowerCase().contains(name), true);
		log.info("API response is successful");

	}
}