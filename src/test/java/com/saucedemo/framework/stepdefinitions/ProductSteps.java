package com.saucedemo.framework.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.saucedemo.framework.drivers.DriverManager;
import com.saucedemo.framework.pages.InventoryPage;
import com.saucedemo.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private LoginPage loginPage() {
        return new LoginPage(DriverManager.getDriver());
    }

    private InventoryPage inventoryPage() {
        return new InventoryPage(DriverManager.getDriver());
    }

    @Given("user is logged in as standard user")
    public void userIsLoggedInAsStandardUser() {
        loginPage().open();
        loginPage().login("standard_user", "secret_sauce");
        assertThat(inventoryPage().isLoaded())
                .as("Inventory page should load for the standard user")
                .isTrue();
    }

    @Then("user should see the product {string} in the inventory list")
    public void userShouldSeeTheProductInTheInventoryList(String productName) {
        assertThat(inventoryPage().hasProductNamed(productName))
                .as("Expected inventory to contain product %s", productName)
                .isTrue();
    }

    @Then("the inventory should list at least {int} products")
    public void theInventoryShouldListAtLeastProducts(int minimumProductCount) {
        assertThat(inventoryPage().getProductCount())
                .as("Inventory should contain at least %s products", minimumProductCount)
                .isGreaterThanOrEqualTo(minimumProductCount);
    }

    @When("user adds the product {string} to the cart")
    public void userAddsTheProductToTheCart(String productName) {
        inventoryPage().addProductToCart(productName);
    }

    @Then("the cart badge should show {int}")
    public void theCartBadgeShouldShow(int expectedCount) {
        assertThat(inventoryPage().header().hasCartBadgeCount(expectedCount))
                .as("Cart badge should eventually show %s", expectedCount)
                .isTrue();
        assertThat(inventoryPage().header().getCartBadgeCount()).isEqualTo(expectedCount);
    }

    @Then("the product {string} should have a visible price")
    public void theProductShouldHaveAVisiblePrice(String productName) {
        assertThat(inventoryPage().getProduct(productName).getPrice())
                .as("Product %s should display a price", productName)
                .isNotBlank();
    }

    @Then("the product {string} should have a visible description")
    public void theProductShouldHaveAVisibleDescription(String productName) {
        assertThat(inventoryPage().getProduct(productName).getDescription())
                .as("Product %s should display a description", productName)
                .isNotBlank();
    }

    @Then("the product {string} should be marked as added to the cart")
    public void theProductShouldBeMarkedAsAddedToTheCart(String productName) {
        assertThat(inventoryPage().isProductAddedToCart(productName))
                .as("Product %s should switch to the added-to-cart state", productName)
                .isTrue();
    }
}
