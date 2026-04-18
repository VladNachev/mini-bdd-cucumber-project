package com.saucedemo.framework.drivers;

public enum BrowserType {
    CHROME,
    EDGE,
    FIREFOX;

    public static BrowserType from(String value) {
        return BrowserType.valueOf(value.trim().toUpperCase());
    }
}
