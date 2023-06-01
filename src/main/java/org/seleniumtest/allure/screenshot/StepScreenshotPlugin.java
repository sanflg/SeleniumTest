package org.seleniumtest.allure.screenshot;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepFinished;
import org.seleniumtest.DriverManager;

public class StepScreenshotPlugin implements ConcurrentEventListener {

    private void handleTestStepFinished(TestStepFinished event) {
        if (System.getProperty("screenshot").equals("onStep") &&
                event.getTestStep().getCodeLocation().contains("stepDefinitions")) {
            ScreenshotTaker.takeAndAttachScreenshot(DriverManager.getInstance().getDriver());
        }
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }
}