package org.seleniumtest.allure;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Properties;

public class AllureManager {
    private static final Properties PROPERTIES = System.getProperties();
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    // TODO add classloader reference for resources
    private static final String OUTPUT_DIR = "#target#allure-results#".replace("#", SEPARATOR);
    private static final String EXECUTOR_DIR = "#src#main#resources#executors#".replace("#", SEPARATOR);
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String[] PARAMS = new String[]{
            "java.vendor.version",
            "driver",
            "maximize",
            "threadCount,",
            "timeout"};
    private static final Logger LOGGER = LogManager.getLogger(AllureManager.class);

    private AllureManager(){}

    public static void setAllureInfo(String executorFileName) {
        addEnvironmentInfo();
        addExecutorInfo(executorFileName);
        addCategoriesInfo();
    }

    private static void addEnvironmentInfo() {
        File file = new File(PROJECT_DIR + OUTPUT_DIR + "environment.properties");

        try {
            if (!file.exists()) {
                for (String param : PARAMS) {
                    FileUtils.writeStringToFile(file,
                            String.format("%s=%s%n",
                                    param,
                                    PROPERTIES.get(param)),
                            StandardCharsets.UTF_8,
                            true);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Unable to write environment info on environment.properties file", e);
        }
    }

    private static void addExecutorInfo(String executorFileName) {
        File executorFile = new File(PROJECT_DIR + EXECUTOR_DIR + executorFileName + ".json");
        File file = new File(PROJECT_DIR + OUTPUT_DIR + "executor.json");

        try {
            if (!file.exists()) {
                String executorInfo = FileUtils.readFileToString(executorFile, StandardCharsets.UTF_8);
                FileUtils.writeStringToFile(file,
                        executorInfo,
                        StandardCharsets.UTF_8,
                        true);
            }
        } catch (IOException e) {
            LOGGER.error("Unable to write executor info on executor.json file", e);
        }
    }

    private static void addCategoriesInfo() {
        File file = new File(PROJECT_DIR + OUTPUT_DIR + "categories.json");
        try {
            if (!file.exists()) {
                File info = new File(PROJECT_DIR +
                        "#src#main#resources#allureCategories.txt".replace("#", SEPARATOR));

                FileUtils.writeStringToFile(file,
                        FileUtils.readFileToString(info, StandardCharsets.UTF_8),
                        StandardCharsets.UTF_8,
                        true);
            }
        } catch (IOException e) {
            LOGGER.error("Unable to add categories info on categories.json file", e);
        }
    }

    public static void fetchLogFile(String nameFile) {
        File file = new File(PROJECT_DIR + OUTPUT_DIR + "logs" + SEPARATOR + nameFile + ".log");
        byte[] fileBytes = null;
        try {
            fileBytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            LOGGER.error("Unable to fetch log file", e);
        }
        Allure.getLifecycle().addAttachment("Log", "text/html", "txt", fileBytes);

    }
}
