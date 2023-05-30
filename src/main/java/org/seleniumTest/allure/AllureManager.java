package org.seleniumTest.allure;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Properties;

public class AllureManager {
    private static final Properties PROPERTIES = System.getProperties();
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    //TODO use Paths and less ugly implementation
    private static final String outputDir = "#target#allure-results#".replace("#", SEPARATOR);
    //TODO add classloader reference for resources
    private static final String executorDir = "#src#main#resources#executors#".replace("#", SEPARATOR);
    private static final String projectDir = System.getProperty("user.dir");
    //TODO Obtain maven parameters for dynamic construction and blur sensitive params
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

    private static void addEnvironmentInfo() {
        File file = new File(projectDir + outputDir + "environment.properties");

        try {
            if (!file.exists()) {
                for (String param : PARAMS) {
                    FileUtils.writeStringToFile(file,
                            String.format("<%s>=<%s>\n",
                                    param,
                                    PROPERTIES.get(param)),
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
        File file = new File(projectDir + outputDir + "categories.json");
        try {
            if (!file.exists()) {
                File info = new File(projectDir +
                        "#src#main#resources#allureCategories.txt".replace("#", SEPARATOR));

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
