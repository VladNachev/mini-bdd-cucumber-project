Feature: Products
  As a logged in SauceDemo user
  I want to interact with products
  So that I can review inventory and add items to my cart

  Scenario: Inventory shows available products
    Given user is logged in as standard user
    Then the inventory should list at least 6 products
    And user should see the product "Sauce Labs Backpack" in the inventory list
    And the product "Sauce Labs Backpack" should have a visible price
    And the product "Sauce Labs Backpack" should have a visible description

  Scenario: Inventory includes multiple known products
    Given user is logged in as standard user
    Then user should see the product "Sauce Labs Backpack" in the inventory list
    And user should see the product "Sauce Labs Bike Light" in the inventory list
    And the product "Sauce Labs Bike Light" should have a visible price
    And the product "Sauce Labs Bike Light" should have a visible description

  Scenario: User adds a product to the cart from inventory
    Given user is logged in as standard user
    When user adds the product "Sauce Labs Backpack" to the cart
    Then the product "Sauce Labs Backpack" should be marked as added to the cart
