package com.saucedemo.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String PAGE_PATH = "/";

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By LOGIN_LOGO = By.className("login_logo");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open(PAGE_PATH);
    }

    public boolean isLoaded() {
        return isVisible(LOGIN_LOGO);
    }

    public void login(String username, String password) {
        type(USERNAME_INPUT, username);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return readText(ERROR_MESSAGE);
    }
}
