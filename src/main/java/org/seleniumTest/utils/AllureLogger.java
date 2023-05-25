package org.seleniumTest.utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureLogger {
    private final Logger LOGGER;

    public AllureLogger(Class<?> clazz){
        LOGGER = LogManager.getLogger(clazz);
    }

    @Step("{0}: {1}")
    public void log(Level level, String message){
        LOGGER.log(level, message);
    }
}
