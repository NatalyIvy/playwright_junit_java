package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.config.TestBase;
import org.example.steps.LoginPageSteps;
import org.example.steps.ProductsPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Products Page")
@DisplayName("Products Page features")
public class ProductsPageTests extends TestBase {
    LoginPageSteps loginPageSteps;
    ProductsPageSteps productsPageSteps;

    @BeforeEach
    void loginAsStandardUser() {
        loginPageSteps = new LoginPageSteps(page);
        loginPageSteps.openUrlAndLoginAsStandardUser();
        productsPageSteps = new ProductsPageSteps(page);
    }

    @Feature("Products filter")
    @DisplayName("Validate products filter dropdown appearance")
    @Test
    public void validateProductsFilter() {
        List<String> expectedOptions = List.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)");

        productsPageSteps.validateFilterSelectedOption("Name (A to Z)");
        productsPageSteps.clickOnFilter();
        productsPageSteps.validateFilterOptions(expectedOptions);
    }

    @Feature("Product card")
    @DisplayName("Validate product card appearance")
    @ParameterizedTest
    @MethodSource("org.example.test_data.ProductDetailsData#provideProductCardInfo")
    public void validateProductCardAppearance(String productName, String description, String price) {
        productsPageSteps.validateDescriptionByProductName(productName, description);
        productsPageSteps.validatePriceByProductName(productName, price);
        productsPageSteps.validateProductByNameHasAddToCardButton(productName);
    }
}
