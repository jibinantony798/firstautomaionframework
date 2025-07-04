package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","summary","html:target/cucumber_report.html"},
        glue = {"stepdefinitions", "utilities"},
        features = {"src/test/resources/features"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class JunitRunner  {

}
