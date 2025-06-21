package pagelocators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SamplePage {

    public WebDriver driver;

    public SamplePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//a[normalize-space(text())='Shop Now'])[1]") public WebElement shopNowButton;

    @FindBy(xpath = "//a[contains(@aria-label, 'Blue Shoes')]")public WebElement addToCartButton;

    @FindBy(css = "span.count")public WebElement cartCount;
}
