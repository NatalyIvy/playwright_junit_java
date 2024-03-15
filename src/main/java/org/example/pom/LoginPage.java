package org.example.pom;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

import java.util.Objects;

@Getter
public class LoginPage extends BasePage {

    public LoginPage(Page pw) {super(pw);}

    Locator passwordInput = pw.locator("[data-test='password']");
    Locator loginButton = pw.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    Locator usernameInput = pw.getByPlaceholder("Username");
    Locator error = pw.locator("[data-test='error']");




//    public LoginPage(Page page) {
//        Objects.requireNonNull(page, "Page should exist");
//        this.page = page;
//        //this.usernameInput = page.getByPlaceholder("Username");
//        //this.passwordInput = page.locator("[data-test='password']");
//        //this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
//        //this.error = page.locator("[data-test='error']");
//    }


}
