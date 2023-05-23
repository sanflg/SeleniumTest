package org.seleniumTest.utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class ProjectLogger {
    private final Logger logger;

    public ProjectLogger(Class<?> clazz){
        logger = LogManager.getLogger(clazz);
    }

    @Step("Level: INFO - Message: {message}")
    public void log(String message){
        logger.info(String.format(message));
    }

    @Step("Level: ERROR - Message: {message} - Exception: {e}")
    public void log(String message, Throwable e) throws RuntimeException {
        logger.error(String.format(message), e);
        throw new RuntimeException(e);
    }
}
