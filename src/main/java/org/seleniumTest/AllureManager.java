package org.seleniumTest;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Properties;

public class AllureManager {
    private static final Properties properties = System.getProperties();
    private static final String separator = FileSystems.getDefault().getSeparator();
    private static final String outputDir = "#target#allure-results#".replace("#", separator);
    private static final String executorDir = "#src#main#resources#executors#".replace("#", separator);
    private static final String projectDir = System.getProperty("user.dir");
    private static final String[] PARAMS = new String[]{
            "java.vendor.version",
            "driver",
            "maximize",
            "threadCount"};

    public static void setAllureInfo(String executorFileName){
        addEnvironmentInfo();
        addExecutorInfo(executorFileName);
        addCategoriesInfo();
    }

    public static void attachScreenshotOnFailure(boolean testFailed){
        if (testFailed) {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            ByteArrayInputStream src = new ByteArrayInputStream(ts.getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Screenshot", src);
        }
    }

    private static void addEnvironmentInfo() {
        File file = new File(projectDir + outputDir + "environment.properties");

        try {
            if (!file.exists()) {
                for (String param : PARAMS) {
                    FileUtils.writeStringToFile(file,
                            String.format("%s=%s\n",
                                    param,
                                    properties.get(param)),
                            StandardCharsets.UTF_8,
                            true);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addExecutorInfo(String executorFileName) {
        File executorFile = new File(projectDir +  executorDir + executorFileName + ".json");
        File file = new File(projectDir + outputDir + "executor.json");

        try {
            if (!file.exists()) {
                String executorInfo = FileUtils.readFileToString(executorFile, StandardCharsets.UTF_8);
                FileUtils.writeStringToFile(file,
                        executorInfo,
                        StandardCharsets.UTF_8,
                        true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addCategoriesInfo() {
        //TODO check how to consume jenkins vars and use jackson for json formatting.
        File file = new File(projectDir + outputDir + "categories.json");
        try {
            if (!file.exists()) {
                File info = new File(projectDir +
                        "#src#main#resources#allureCategories.txt".replace("#", separator));

                FileUtils.writeStringToFile(file,
                        FileUtils.readFileToString(info,StandardCharsets.UTF_8),
                        StandardCharsets.UTF_8,
                        true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
