package org.example.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;

/*
@TestInstance(TestInstance.Lifecycle.PER_CLASS) annotation to make JUnit create one instance of a class for all test
methods within that class (by default each JUnit will create a new instance of the class for each test method).
Store Playwright and Browser objects in instance fields. They will be shared between tests.
Each instance of the class will use its own copy of Playwright.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestListener.class)
public abstract class TestBase {
    public static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    public static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    public static final ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    public static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static synchronized Playwright getPlaywright() {
        if (playwrightThreadLocal.get() == null) {
            Playwright playwright = Playwright.create();
            playwrightThreadLocal.set(playwright);
        }
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser() {
        if (browserThreadLocal.get() == null) {
            String browserName = "chromium";
            BrowserType browserType = getBrowserType(getPlaywright(), browserName);
            Browser browser = browserType.launch();
            browserThreadLocal.set(browser);
        }
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        if (browserContextThreadLocal.get() == null) {
            String browserName = "chromium";

            BrowserType browserType = getBrowserType(getPlaywright(), browserName);
            Browser browser = browserType.launch();

            Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();
            newContextOptions.acceptDownloads = true;
            BrowserContext browserContext = browser.newContext(newContextOptions);
            browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("loginAuthToken.json")));
            browserContextThreadLocal.set(browserContext);
        }
        return browserContextThreadLocal.get();
    }

    @SneakyThrows
    public static synchronized Page getPage() {
        if (pageThreadLocal.get() == null) {
            Page page = getBrowserContext().newPage();
            pageThreadLocal.set(page);
        }
        return pageThreadLocal.get();
    }

    @SneakyThrows
    private static synchronized BrowserType getBrowserType(Playwright playwright, String browserName) {
        return switch (browserName) {
            case "chromium" -> playwright.chromium();
            case "webkit" -> playwright.webkit();
            case "firefox" -> playwright.firefox();
            default -> throw new IllegalAccessException("Indicate valid browser name: [chromium, webkit, firefox]");
        };
    }

    @BeforeAll
    void launchBrowser() {
        getPlaywright();
        getBrowser();
    }

    @AfterAll
    void closeBrowser() {
        getBrowser().close();
        getPlaywright().close();
        browserThreadLocal.remove();
        playwrightThreadLocal.remove();
    }

    @BeforeEach
    void createContextAndPage() {
        getBrowserContext();
        getPage();
    }

    @AfterEach
    void closeContext() {
        getBrowserContext().close();
        getPage().close();
        pageThreadLocal.remove();
        browserContextThreadLocal.remove();
    }

    public static void takeScreenShot(String description) {
        Allure.getLifecycle().addAttachment(
                description + " screenshot", "image/png", "png",
                getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true))
        );
    }
    //public static synchronized Page createPage(Playwright playwright) {
//        String browserName = "chromium";
//        BrowserType browserType = getBrowserType(playwright, browserName);
//        Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
//
//        Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();
//        newContextOptions.acceptDownloads = true;
//        BrowserContext browserContext = browser.newContext(newContextOptions);
//        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("loginAuthToken.json")));
//
//        browserTypeThreadLocal.set(browserType);
//        browserThreadLocal.set(browser);
//        browserContextThreadLocal.set(browserContext);
//
//        return browserContext.newPage();
//    }
//
//    public static synchronized void closePage() {
//        Playwright playwright = playwrightThreadLocal.get();
//        Page page = pageThreadLocal.get();
//        if (playwright != null) {
//            page.close();
//            pageThreadLocal.remove();
//            playwright.close();
//            playwrightThreadLocal.remove();
//        }
//    }
}
