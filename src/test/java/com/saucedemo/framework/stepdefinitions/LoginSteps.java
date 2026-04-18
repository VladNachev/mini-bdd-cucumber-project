package com.saucedemo.framework.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.saucedemo.framework.drivers.DriverManager;
import com.saucedemo.framework.pages.InventoryPage;
import com.saucedemo.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private LoginPage loginPage() {
        return new LoginPage(DriverManager.getDriver());
    }

    private InventoryPage inventoryPage() {
        return new InventoryPage(DriverManager.getDriver());
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        loginPage().open();
        assertThat(loginPage().isLoaded())
                .as("Login page should be visible")
                .isTrue();
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        loginPage().login(username, password);
    }

    @Then("user should see the inventory page")
    public void userShouldSeeTheInventoryPage() {
        assertThat(inventoryPage().isLoaded())
                .as("Inventory page should load after successful login")
                .isTrue();
        assertThat(inventoryPage().header().isVisible())
                .as("Header should be displayed")
                .isTrue();
        assertThat(inventoryPage().footer().isVisible())
                .as("Footer should be displayed")
                .isTrue();
        assertThat(inventoryPage().getProductCount())
                .as("Inventory should contain products")
                .isGreaterThan(0);
    }

    @Then("locked out error message should be displayed as {string}")
    public void lockedOutErrorMessageShouldBeDisplayedAs(String expectedMessage) {
        assertThat(loginPage().getErrorMessage()).isEqualTo(expectedMessage);
    }
}
