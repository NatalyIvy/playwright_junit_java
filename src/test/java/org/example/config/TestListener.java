package org.example.config;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestListener implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // check the browserContext for an exception
        boolean passed = context.getExecutionException().isEmpty();
        if (!passed) {
            TestBase.takeScreenShot("FAIL step");
        }
    }
}