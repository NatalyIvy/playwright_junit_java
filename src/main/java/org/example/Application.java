package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Application {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
            Browser browser = playwright.chromium().launch(launchOptions);
            Page page = browser.newPage();
            browser.startTracing(page);
            page.navigate("https://playwright.dev/");

// Expect a title "to contain" a substring.
            assertThat(page).hasTitle(Pattern.compile("Playwright"));
// create a locator
            Locator getStartedButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));

 // Expect an attribute "to be strictly equal" to the value.
            assertThat(getStartedButton).hasAttribute("href", "/docs/intro");

            getStartedButton.click();

            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();




            //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));


        }
    }
}
