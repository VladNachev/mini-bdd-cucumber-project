package com.saucedemo.framework.utils;

import com.saucedemo.framework.config.TestConfig;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private final WebDriver driver;
    private final Duration timeout;

    public WaitUtils(WebDriver driver) {
        this(driver, TestConfig.getExplicitWaitTimeout());
    }

    public WaitUtils(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    public WebElement waitForVisible(By locator) {
        return buildWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return buildWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForUrlContains(String partialUrl) {
        return buildWait().until(ExpectedConditions.urlContains(partialUrl));
    }

    public boolean waitForText(By locator, String text) {
        return buildWait().until(ExpectedConditions.textToBe(locator, text));
    }

    public boolean waitForInvisible(By locator) {
        return buildWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebDriverWait buildWait() {
        return new WebDriverWait(driver, timeout);
    }
}
