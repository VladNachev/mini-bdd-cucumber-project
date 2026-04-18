package com.saucedemo.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public final class TestConfig {

    private static final String CONFIG_FILE = "config/application.properties";
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file not found: " + CONFIG_FILE);
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load configuration from " + CONFIG_FILE, exception);
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String environmentKey = key.toUpperCase().replace('.', '_');
        String environmentValue = System.getenv(environmentKey);
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }

        return Objects.requireNonNull(PROPERTIES.getProperty(key), "Missing configuration key: " + key);
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static Duration getExplicitWaitTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("explicit.wait.seconds")));
    }

    public static Duration getPageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("page.load.timeout.seconds")));
    }

    public static Duration getScriptTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("script.timeout.seconds")));
    }

    public static String getStandardUsername() {
        return get("users.standard.username");
    }

    public static String getLockedOutUsername() {
        return get("users.locked.username");
    }

    public static String getPassword() {
        return get("users.password");
    }
}
