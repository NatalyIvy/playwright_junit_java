package org.example.pom;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class LoginPage extends BasePage {

    public LoginPage(Page pw) {
        super(pw);
    }

    Locator passwordInput = pw.locator("[data-test='password']");
    Locator loginButton = pw.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    Locator usernameInput = pw.getByPlaceholder("Username");
    Locator error = pw.locator("[data-test='error']");

    public void clickOnLoginButton() {
        this.loginButton.click();
    }
}
