package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.helpers.annotations.CustomStepAspect;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class TestListener implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // check the context for an exception
        boolean passed = context.getExecutionException().isEmpty();
        if (!passed) {
            String stepName = CustomStepAspect.getStepName(); // Get the step name
            TestBase.takeScreenShot("FAIL step");
            log.info("[FAIL STEP] " + stepName); // Log the step name
        }
    }
}