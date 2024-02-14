package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PWTests {
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext browserContext;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();
        page = browserContext.newPage();

    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @Test
    @DisplayName("Open playwright main page and get started")
    void openPwPage() {
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page.navigate("https://playwright.dev/");
        assertThat(page).hasTitle(Pattern.compile("Playwright"));

        Locator getStartedButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));
        getStartedButton.click();

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();
    }
}
