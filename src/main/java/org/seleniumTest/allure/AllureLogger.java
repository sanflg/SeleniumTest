package org.seleniumTest.allure;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.StringWriter;

public class AllureLogger {
    private final Logger logger;
    private final StringWriter stringWriter = new StringWriter();
    private final WriterAppender testLogger = WriterAppender.newBuilder().setName("Allure").setTarget(stringWriter).build();

    public AllureLogger(Class<?> clazz){
        createLogger();
        logger = LogManager.getLogger(clazz);
        testLogger.start();
    }

    private void createLogger() {

        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();

        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %level [%t] [%c] [%M] [%l] - %msg%n").build();

        WriterAppender writerAppender = WriterAppender.newBuilder().setName("writeLogger").setTarget(stringWriter)
                .setLayout(layout).build();
        writerAppender.start();
        config.addAppender(writerAppender);

        AppenderRef ref = AppenderRef.createAppenderRef("writeLogger", null, null);
        AppenderRef[] refs = new AppenderRef[] { ref };

        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "example", null, refs, null, config,
                null);

        loggerConfig.addAppender(writerAppender, null, null);
        config.addLogger("example", loggerConfig);
        ctx.updateLoggers();
    }
}
