Feature: Login
  As a SauceDemo user
  I want to authenticate with my account
  So that I can access the inventory page

  Scenario: Successful login with standard user
    Given user is on login page
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should see the inventory page

  Scenario: Login with locked out user
    Given user is on login page
    When user logs in with username "locked_out_user" and password "secret_sauce"
    Then locked out error message should be displayed as "Epic sadface: Sorry, this user has been locked out."
