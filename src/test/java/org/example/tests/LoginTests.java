package org.example.tests;

import com.microsoft.playwright.assertions.LocatorAssertions;
import org.example.pom.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Swag Labs login page tests")
public class LoginTests extends GeneralExecutionHooks {
    LoginPage loginPage;

    private static final String PAGE_TITLE = "Swag Labs";

    @BeforeEach
    void navigate() {
        loginPage = new LoginPage(page);
        loginPage.navigateTo();
    }

    @Test
    @Order(1)
    @DisplayName("Open Swag login page and assert page elements")
    void openLoginAndAssertWebElements() {
        assertThat(page).hasTitle(PAGE_TITLE);
        assertThat(loginPage.getUsernameInput()).isVisible();
        assertThat(loginPage.getUsernameInput()).hasAttribute("placeholder", "Username");

        assertThat(loginPage.getPasswordInput()).hasAttribute(
                "placeholder",
                "password",
                new LocatorAssertions.HasAttributeOptions().setIgnoreCase(true));
    }

    @Order(3)
    @Tag("testTag")
    @RepeatedTest(2)
    @DisplayName("Open Swag login page and try to login without data")
    void loginWithoutAccount() {
        loginPage.getLoginButton().click();
        assertThat(loginPage.getError()).hasText("Epic sadface: Username is required");
        assertThat(page).hasTitle(PAGE_TITLE);
    }

    @Test
    @Order(2)
    @DisplayName("Open Swag login page and login with existing account")
    void login() {
        loginPage.getUsernameInput().fill("standard_user");
        loginPage.getPasswordInput().fill("secret_sauce");
        loginPage.getLoginButton().click();
        assertThat(loginPage.getUsernameInput()).not().isVisible();
    }
}
