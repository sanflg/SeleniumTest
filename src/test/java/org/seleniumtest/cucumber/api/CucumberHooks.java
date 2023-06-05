package org.seleniumtest.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    @Before
    public void cucumberBefore(Scenario scenario) {
        Thread.currentThread().setName(scenario.getName().replace(" ", "_"));
    }
}
