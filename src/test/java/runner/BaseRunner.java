package runner;

import java.util.logging.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import cucumber.api.CucumberOptions;

@CucumberOptions(glue = "stepdef", plugin = { "json:target/json-cucumber-reports/cukejson.json",
		"testng:target/testng-cucumber-reports/cuketestng.xml",
		"rerun:target/rerun.txt" }, features = "src/test/resources/feature", monochrome = false, strict = true, dryRun = false, tags = {
	 "@ApiTests   or @WebUiTests"  })
public class BaseRunner extends AbstractTestNGCucumberParallelTests {

	private static long duration;
	static Logger log = Logger.getLogger(BaseRunner.class.getName());

	@BeforeClass
	public static void before() {
		duration = System.currentTimeMillis();
		log.info("Thread Id �| Scenario Num � � � | Step Count");
	}

	@AfterClass
	public static void after() {
		duration = System.currentTimeMillis() - duration;
		log.info("DURATION - " + duration);
	}
}