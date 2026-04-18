package com.saucedemo.framework.components;

import com.saucedemo.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    private static final By APP_LOGO = By.className("app_logo");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isVisible() {
        return isVisible(APP_LOGO);
    }

    public void openCart() {
        click(CART_LINK);
    }

    public int getCartBadgeCount() {
        if (!isVisible(CART_BADGE)) {
            return 0;
        }
        return Integer.parseInt(readText(CART_BADGE));
    }

    public boolean hasCartBadgeCount(int expectedCount) {
        try {
            return wait.waitForText(CART_BADGE, String.valueOf(expectedCount));
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public void logout() {
        click(MENU_BUTTON);
        click(LOGOUT_LINK);
    }
}
