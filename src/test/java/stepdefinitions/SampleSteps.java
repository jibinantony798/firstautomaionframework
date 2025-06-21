package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pagelocators.SamplePage;
import utilities.GenericFn;

public class SampleSteps extends GenericFn{
    SamplePage samplePage;

    @Given("user adds item to cart")
    public void userAddsItemToCart() {
        System.out.println(driver);
        samplePage = new SamplePage(getDriver());
        click(samplePage.shopNowButton);
        if (samplePage.cartCount.getText().equalsIgnoreCase("0")) {
            click(samplePage.addToCartButton);
        }
    }

    @Then("user able to see the item count")
    public void userAbleToSeeTheItemCount() {
        if (samplePage.cartCount.getText().equalsIgnoreCase("1")) {
            System.out.println("Success");
        } else {
            System.out.println("Cart count not updated.");
        }
    }
}
