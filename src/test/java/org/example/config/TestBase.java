package org.example.config;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import java.nio.file.Path;
import java.util.Date;
import lombok.SneakyThrows;
import org.example.helpers.env.PropertiesAccessor;
import org.example.tests.LoginTests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestListener.class)
public abstract class TestBase {
  protected Playwright playwright;
  protected Browser browser;
  protected BrowserContext context;
  protected static Page page;

  private static final String BROWSER_TYPE =
      PropertiesAccessor.getInstance().values().getBrowserType();
  private static final boolean HEADLESS_MODE =
      PropertiesAccessor.getInstance().values().getHeadlessMode();
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginTests.class);

  @BeforeAll
  void launchBrowser() {
    playwright = Playwright.create();
    BrowserType browserType = getBrowserType(playwright);
    browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(HEADLESS_MODE));
  }

  @AfterAll
  void closeBrowser() {
    browser.close();
    playwright.close();
  }

  @BeforeEach
  void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
    context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
  }

  @AfterEach
  void closeContext() {
    context.close();
    page.close();
  }

  @SneakyThrows
  private static synchronized BrowserType getBrowserType(Playwright playwright) {
    return switch (TestBase.BROWSER_TYPE) {
      case "chromium" -> playwright.chromium();
      case "webkit" -> playwright.webkit();
      case "firefox" -> playwright.firefox();
      default ->
          throw new IllegalAccessException(
              "Indicate valid browser name: [chromium, webkit, firefox]");
    };
  }

  public static void takeScreenShot(String description) {
    Path path = Path.of("screenAfterTest.png");
    page.screenshot(new Page.ScreenshotOptions().setPath(path));
    ReportPortal.emitLog(description, LogLevel.INFO.name(), new Date(), path.toFile());
  }
}
