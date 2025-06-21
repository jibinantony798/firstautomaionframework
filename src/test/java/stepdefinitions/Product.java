package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Product {

    @Given("user find item in cart")
    public void userFindItemInCart() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Find the item in cart");
    }
    @Then("user able to see the item")
    public void userAbleToSeeTheItem() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Able to see item in the cart");
    }
}
