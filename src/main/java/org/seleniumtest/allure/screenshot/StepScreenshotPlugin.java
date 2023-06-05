package org.seleniumtest.allure.screenshot;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepFinished;
import org.seleniumtest.DriverManager;

/**
 * StepScreenshotPluging is used to override the behavior of ConcurrentEventListener in the testStep is finished.
 */
public class StepScreenshotPlugin implements ConcurrentEventListener {

    /**
     * If screenshot config is set to step and step is from an stepDefinition class directory, takes te screenshot.
     * The event limitation was added to avoid every step in the hierarchy to contain a screenshot since this
     * implementation is excessive and consumes resources.
     *
     * @param event After test step execution event.
     */
    private void handleTestStepFinished(TestStepFinished event) {
        if (System.getProperty("screenshot").equals("onStep") &&
                event.getTestStep().getCodeLocation().contains("stepDefinitions")) {
            ScreenshotTaker.takeAndAttachScreenshot(DriverManager.getInstance().getDriver());
        }
    }

    /**
     * @param publisher to register the test step finished implementation.
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }
}