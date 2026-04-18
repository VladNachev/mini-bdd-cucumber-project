# Mini BDD Cucumber Framework

## Stack

- Java 21
- Maven
- Selenium WebDriver
- Cucumber JVM
- JUnit 5 Platform Suite
- AssertJ

## Project Structure

```text
src
|-- test
|   |-- java
|   |   `-- com/saucedemo/framework
|   |       |-- components
|   |       |-- config
|   |       |-- drivers
|   |       |-- hooks
|   |       |-- pages
|   |       |-- runners
|   |       |-- stepdefinitions
|   |       `-- utils
|   `-- resources
|       |-- config
|       |-- features
|       `-- junit-platform.properties
```

## Design Principles

- Features contain business behavior only.
- Step definitions stay thin and delegate to page objects.
- Page objects own locators and UI actions.
- Components model reusable areas like header, footer, and product cards.
- Driver lifecycle is isolated in hooks and a thread-safe driver manager.

## What Is Included

- Login feature with positive and negative coverage
- Reusable `BasePage`
- `DriverFactory` and `DriverManager` with `ThreadLocal` support for parallel execution
- Hooks with `@Before` and `@After`
- Config-driven browser and environment settings
- HTML and JSON Cucumber reports under `target/cucumber-reports`

## Run From IntelliJ

1. Import the project as a Maven project.
2. Make sure Chrome, Edge, or Firefox is installed.
3. Open `src/test/resources/config/application.properties` and set the browser if needed.
4. Run the suite class: `com.saucedemo.framework.runners.RunCucumberTestSuite`

## Run From Maven

```bash
mvn clean test
```
