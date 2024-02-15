package pom;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

import java.util.Objects;

@Getter
public class LoginPage {
    private final Page page;
    Locator passwordInput;
    Locator loginButton;
    Locator usernameInput;
    Locator error;

    public LoginPage(Page page) {
        Objects.requireNonNull(page, "Page should exist");
        this.page = page;
        this.usernameInput = page.getByPlaceholder("Username");
        this.passwordInput = page.locator("[data-test='password']");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.error = page.locator("[data-test='error']");
    }

    public void navigateTo() {
        page.navigate("https://www.saucedemo.com/");
    }
}
