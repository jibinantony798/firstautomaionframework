package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class GenericFn {

    public static ThreadLocal<WebDriver> driver=new ThreadLocal<>();

    public static String path;
    File file=new File("config/serenity.properties");

    public static Properties prop=new Properties();


    public void setUpFile(){
        try{
            FileInputStream fileInput;
            fileInput=new FileInputStream(file);
            prop.load(fileInput);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void browser_Init() {
        try {
            setUpFile(); // Load your browser config (e.g., from a properties file)
            path = getDriverPath().trim().toLowerCase();

            switch (path) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setAcceptInsecureCerts(true);
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver.set(new EdgeDriver(edgeOptions));
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver.set(new ChromeDriver(chromeOptions));
                }
            }
            getDriver().manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches browser based on config, navigates to welcome page, and assigns the driver.
     */

//    public void browserLaunch() {
//        try {
//            driver = browser_Init(); // assign the initialized driver
//            driver.navigate().to(getURL());
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public void browserLaunch() {
        browser_Init();
        getDriver().navigate().to(getURL());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    /** Gets the thread-safe WebDriver instance */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /** Clean up thread-local driver */
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    /* Reading chrome driver path from config.properties file */
    public  String getDriverPath() {
        String driver= prop.getProperty("Browser");
        if(driver!=null) return driver ;
        else throw new RuntimeException ("Driver is not specified in the Config.properties");
    }

    public  String getURL() {
        String URL= prop.getProperty("URL");
        if(URL!=null) return URL ;
        else throw new RuntimeException ("URL is not specified in the Config.properties");
    }

    /** Switch to a frame by WebElement */
    public void switchToFrame(WebElement frameElement) {
        getDriver().switchTo().frame(frameElement);
    }

    /** Switch to a frame by index */
    public void switchToFrame(int index) {
        getDriver().switchTo().frame(index);
    }

    /** Switch to a frame by name or ID */
    public void switchToFrame(String nameOrId) {
        getDriver().switchTo().frame(nameOrId);
    }

    /** Switch back to the main document from a frame */
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    /** Switch to the alert box */
    public Alert switchToAlert() {
        return getDriver().switchTo().alert();
    }

    /** Accept the alert (OK) */
    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    /** Dismiss the alert (Cancel) */
    public void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    /** Switch to the latest opened window/tab */
    public void switchToLatestWindow() {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
    }

    /** Switch to a window using its title */
    public void switchToWindowByTitle(String expectedTitle) {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
            if (getDriver().getTitle().equalsIgnoreCase(expectedTitle)) {
                break;
            }
        }
    }

    /** Click on element */
    public void click(WebElement element) {
        element.click();
    }

    /** Refresh current page */
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    /** Clear input field using standard clear() */
    public void clear(WebElement element) {
        element.clear();
    }

    /** Clear input field using JavaScript */
    public void clearWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", element);
    }

}
