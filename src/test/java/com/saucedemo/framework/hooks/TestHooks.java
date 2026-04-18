package com.saucedemo.framework.hooks;

import com.saucedemo.framework.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.createDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
