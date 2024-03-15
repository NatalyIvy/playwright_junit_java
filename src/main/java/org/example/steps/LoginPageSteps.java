package org.example.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.pom.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPageSteps {
    public final LoginPage ui;

    private static final String STANDARD_USERNAME = "standard_user";
    private static final String STANDARD_USER_PASS = "secret_sauce";

    public LoginPageSteps(Page pwPage) {
        this.ui = new LoginPage(pwPage);
    }

    @Step("Navigate to SWAG login")
    public void navigate() {
        ui.pw.navigate("https://www.saucedemo.com/");
    }

    @Step("Insert standard username and password, validate login input is not visible any more")
    public void loginAsStandardUser() {
        ui.getUsernameInput().fill(STANDARD_USERNAME);
        ui.getPasswordInput().fill(STANDARD_USER_PASS);
        ui.getLoginButton().click();
        assertThat(ui.getUsernameInput()).not().isVisible();
    }
}
