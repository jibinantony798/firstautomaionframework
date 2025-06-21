Feature: Add product to cart

  Scenario: User adds a product to the cart
    Given user adds item to cart
    Then user able to see the item count
