package org.example.tests;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Swag Labs login page tests")
public class LoginTests extends TestBase {
  LoginPageSteps loginPageSteps;
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginTests.class);

  private static final String PAGE_TITLE = "Swag Labs";

  @BeforeEach
  void init() {
    loginPageSteps = new LoginPageSteps(page);
    loginPageSteps.navigate();
  }

  @Test
  @Order(1)
  @DisplayName("Open Swag login page and assert page elements")
  void openLoginAndAssertWebElements() {
    //LOGGER.info("hello");
    loginPageSteps.assertTitle(PAGE_TITLE);
    loginPageSteps.validateUsernamePlaceholder("Username");
    loginPageSteps.validatePasswordPlaceholder("password");
    //LOGGER.info("User is not logged in");
  }

  @Order(3)
  @Tag("ttt")
  @RepeatedTest(2)
  @DisplayName("Open Swag login page and try to login without data")
  void loginWithoutAccount() {
    loginPageSteps.ui.clickOnLoginButton();
    loginPageSteps.validateLoginPageError("Epic sadface: Username is required");
    loginPageSteps.assertTitle(PAGE_TITLE);
  }

  @Test
  @Order(2)
  @DisplayName("Open Swag login page and login with existing account")
  void login() {
    loginPageSteps.loginAsStandardUser();
  }
}
