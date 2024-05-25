package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//To run cucumber feature file, we have to create a TestNGRunner so we can run the feature file of cucumber
@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinitions",monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
