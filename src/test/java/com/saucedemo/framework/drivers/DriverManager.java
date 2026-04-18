package com.saucedemo.framework.drivers;

import com.saucedemo.framework.config.TestConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void createDriver() {
        WebDriver driver = DriverFactory.createDriver();
        driver.manage().timeouts().pageLoadTimeout(TestConfig.getPageLoadTimeout());
        driver.manage().timeouts().scriptTimeout(TestConfig.getScriptTimeout());
        driver.manage().window().setSize(new Dimension(1920, 1080));
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized for the current thread.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
