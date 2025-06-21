package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        plugin = {"pretty","summary","html:target/cucumber_report.html"},
        glue = {"stepdefinitions", "utilities"},
        features = {"src/test/resources/features"},
//        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
