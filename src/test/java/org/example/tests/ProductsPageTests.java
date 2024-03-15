package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.config.GeneralExecutionHooks;
import org.example.pom.LoginPage;
import org.example.pom.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Products Page features")
@DisplayName("Products Page features")
public class ProductsPageTests extends GeneralExecutionHooks {
    private ProductsPage productsPage;
    LoginPage loginPage;

    @BeforeEach
    void navigate() {
        //LoginPage loginPage = new LoginPage(page);
        //loginPage.navigateTo();
        loginPage.getUsernameInput().fill("standard_user");
        loginPage.getPasswordInput().fill("secret_sauce");
        loginPage.getLoginButton().click();
        //productsPage = new ProductsPage(page);
    }

    @Feature("Products filter")
    @DisplayName("Validate products filter dropdown appearance")
    @Test
    public void validateProductsFilter() {
        List<String> expectedOptions = List.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)");

        assertThat(productsPage.getFilter()).isVisible();
        Assertions.assertEquals(productsPage.getFilterSelectedOption(), "Name (A to Z)");
        productsPage.getFilter().click();

        Assertions.assertEquals(productsPage.getFilterOptions(), expectedOptions);

    }


}
