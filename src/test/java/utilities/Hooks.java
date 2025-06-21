package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks extends GenericFn{

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("BEFORE: THREAD ID:"+Thread.currentThread().threadId()+","+"SCENARIO NAME: "+scenario.getName());
        browserLaunch();
        System.out.println("Driver Instance: " + getDriver());
    }

    @After
    public void tearDown(Scenario scenario) {
            System.out.println("AFTER: THREAD ID:"+Thread.currentThread().threadId()+","+"SCENARIO NAME: "+scenario.getName());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
            quitDriver();

        }

}
