package org.seleniumTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class CucumberSupport {
    @BeforeAll
    //TODO 4 There is a way to get rid of this warning?
    public static void setupAll() {
        DriverManager.setupAll();
    }

    @Before
    public void setup() {
        DriverManager.getDriverManager().setup();
    }

    //TODO 2 Add allure screenshot on failure with a generic implementation (also for testng execution)
    @After
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }
}
