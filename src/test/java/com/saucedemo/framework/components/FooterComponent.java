package com.saucedemo.framework.components;

import com.saucedemo.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent extends BasePage {

    private static final By FOOTER = By.className("footer");

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isVisible() {
        return isVisible(FOOTER);
    }
}
