package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(glue = "stepdef", plugin = { "json:target/json-cucumber-reports/web/cukejson.json",
		"testng:target/testng-cucumber-reports/web/cuketestng.xml" }, features = "src/test/resources/feature/web")
public class TakeALot extends AbstractTestNGCucumberParallelTests {

}