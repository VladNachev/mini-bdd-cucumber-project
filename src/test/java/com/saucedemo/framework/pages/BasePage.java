package com.saucedemo.framework.pages;

import com.saucedemo.framework.config.TestConfig;
import com.saucedemo.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void open(String relativePath) {
        driver.get(TestConfig.getBaseUrl() + relativePath);
    }

    protected void click(By locator) {
        wait.waitForClickable(locator).click();
    }

    protected void clickUsingJs(By locator) {
        WebElement element = wait.waitForVisible(locator);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        WebElement element = wait.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String readText(By locator) {
        return wait.waitForVisible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();
    }

    protected boolean isVisible(By locator) {
        try {
            return wait.waitForVisible(locator).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected WebElement find(By locator) {
        return wait.waitForVisible(locator);
    }
}
