package cucumberOptions;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

//import io.cucumber.junit.platform.engine.Cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features") // folder under src/test/resources
@IncludeEngines("cucumber")
@ConfigurationParameter(key= GLUE_PROPERTY_NAME, value="stepDefination,stepDefination1")
@IncludeTags(value = {"AddProducts ", "LimitSkipProduct", "DeleteProducts"})
//@ExcludeTags("AddProduct")
@ConfigurationParameter(key= PLUGIN_PROPERTY_NAME, value="pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/jsonReports/cucumber-report23.json")


//@RunWith(Cucumber.class)
//@cucumberOptions.Testrunner(features="src/test/resources/feature", glue={"stepDefination"})



public class TestRunner {
	

}
