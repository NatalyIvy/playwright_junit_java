package org.example.steps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.epam.reportportal.annotations.Step;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.example.helpers.env.PropertiesAccessor;
import org.example.pom.LoginPage;

public class LoginPageSteps {
  public final LoginPage ui;

  public LoginPageSteps(Page pwPage) {
    this.ui = new LoginPage(pwPage);
  }

  private static final String STANDARD_USERNAME =
      PropertiesAccessor.getInstance().values().getStandardUsername();
  private static final String STANDARD_USER_PASS =
      PropertiesAccessor.getInstance().values().getStandardPassword();
  private static final String PATH = PropertiesAccessor.getInstance().values().getBaseUrl();

  @Step("Navigate to SWAG login")
  public void navigate() {
    ui.pw.navigate(PATH);
  }

  @Step("OpenSwag and login as standard user")
  public void openUrlAndLoginAsStandardUser() {
    navigate();
    loginAsStandardUser();
  }

  @Step("Insert standard username and password, validate login input is not visible any more")
  public void loginAsStandardUser() {
    ui.getUsernameInput().fill(STANDARD_USERNAME);
    ui.getPasswordInput().fill(STANDARD_USER_PASS);
    ui.getLoginButton().click();
    assertThat(ui.getUsernameInput()).not().isVisible();
  }

  @Step("Validate Login page has [{title}] title ")
  public void assertTitle(String title) {
    assertThat(ui.pw).hasTitle(title);
  }

  @Step(
      value =
          "Validate on Login page the user name input is visible and has placeholder [{placeholder}]")
  public void validateUsernamePlaceholder(String placeholder) {
    assertThat(ui.getUsernameInput()).isVisible();
    assertThat(ui.getUsernameInput()).hasAttribute("placeholder", placeholder);
  }

  @Step("Validate on Login page the password input is visible and has placeholder [{placeholder}]")
  public void validatePasswordPlaceholder(String placeholder) {
    assertThat(ui.getPasswordInput())
        .hasAttribute(
            "placeholder",
            placeholder,
            new LocatorAssertions.HasAttributeOptions().setIgnoreCase(true));
  }

  @Step("Validate on Login page the error is [{error}]")
  public void validateLoginPageError(String error) {
    assertThat(ui.getError()).hasText(error);
  }
}
