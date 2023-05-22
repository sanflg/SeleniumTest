package org.seleniumTest.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Properties;

public class AllureManager {
    private static final String[] PARAMS = new String[]{
            "java.vendor.version",
            "driver",
            "maximize",
            "threadCount"};

    public static void addEnvironment() {
        Properties properties = System.getProperties();

        String separator = FileSystems.getDefault().getSeparator();
        String outputDir = "#target#allure-results#".replace("#", separator);
        String projectDir = System.getProperty("user.dir");

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
        System.out.println("PEPE");

//        for (String param : PARAMS) {
//            try {
//
//                FileUtils.writeStringToFile(file,
//                        String.format("%s=%s\n",
//                                param,
//                                properties.get(param)),
//                        StandardCharsets.UTF_8,
//                        true);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
