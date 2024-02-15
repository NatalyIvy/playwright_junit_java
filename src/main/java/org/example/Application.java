package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Application {
    public static void main(String[] args) {
        //Test example with the Playwright (without JUnit runner)
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
            Browser browser = playwright.chromium().launch(launchOptions);
            Page page = browser.newPage();
            browser.startTracing(page);
            page.navigate("https://playwright.dev/");
            assertThat(page).hasTitle(Pattern.compile("Playwright"));
            Locator getStartedButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));
            assertThat(getStartedButton).hasAttribute("href", "/docs/intro");
            getStartedButton.click();
            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }
    }
}
