package org.example.config;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher {
    private Page page;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment(
                "FAIL step screenshot", "image/png", "png",
                page.screenshot(new Page.ScreenshotOptions().setFullPage(true))
        );
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment(
                "PASS step screenshot", "image/png", "png",
                page.screenshot(new Page.ScreenshotOptions().setFullPage(true))
        );
    }
}