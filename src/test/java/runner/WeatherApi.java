package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(glue = "stepdef", plugin = { "json:target/json-cucumber-reports/api/cukejson.json",
		"testng:target/testng-cucumber-reports/api/cuketestng.xml" }, features = "src/test/resources/feature/api")
public class WeatherApi extends AbstractTestNGCucumberParallelTests {

}