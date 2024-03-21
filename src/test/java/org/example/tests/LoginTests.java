package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.config.TestBase;
import org.example.steps.LoginPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
@Epic("SWAG login validations")
@Feature("Login page")
@DisplayName("Swag Labs login page tests")
public class LoginTests extends TestBase {
    LoginPageSteps loginPageSteps;

    private static final String PAGE_TITLE = "Swag Labs";

    @BeforeEach
    void init() {
        loginPageSteps = new LoginPageSteps(page);
        loginPageSteps.navigate();
    }

    @Test
    @Order(1)
    @Description("Open Swag login page and assert page elements")
    @Severity(SeverityLevel.CRITICAL)
    void openLoginAndAssertWebElements() {
        loginPageSteps.assertTitle(PAGE_TITLE);
        loginPageSteps.validateUsernamePlaceholder("Username");
        loginPageSteps.validatePasswordPlaceholder("password");
    }

    @Order(3)
    @Tag("testTag")
    @RepeatedTest(2)
    @DisplayName("Open Swag login page and try to login without data")
    @Severity(SeverityLevel.CRITICAL)
    void loginWithoutAccount() {
        loginPageSteps.ui.clickOnLoginButton();
        loginPageSteps.validateLoginPageError("Epic sadface: Username is required");
        loginPageSteps.assertTitle(PAGE_TITLE);
    }

    @Test
    @Order(2)
    @DisplayName("Open Swag login page and login with existing account")
    @Severity(SeverityLevel.CRITICAL)
    void login() {
        loginPageSteps.loginAsStandardUser();
    }
}
