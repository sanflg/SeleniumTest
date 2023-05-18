package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import org.seleniumTest.DriverManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TODO Avoid repetition of cucumber scenarios in allure report
//TODO Learn more about allure implementations for cucumber
//TODO Allure categories, environment,
@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.seleniumTest"},
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Regression"
)
//@CucumberOptions(plugin = { "cucumberHooks.customReportListener",
//        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm" }, monochrome = true, glue = { "org.seleniumTest",
//        "cucumberHooks" }, // Packagename
//        features = { "src/test/resources/features" } // FolderName
//)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    //TODO Cucumber parallel
    //TODO More test cases for other suite
    //TODO Add cucumber before to set test capabilities and options
}