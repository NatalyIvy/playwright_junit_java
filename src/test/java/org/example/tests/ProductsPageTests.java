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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Products Page")
@DisplayName("Products Page features")
public class ProductsPageTests extends TestBase {
    LoginPageSteps loginPageSteps;
    ProductsPageSteps productsPageSteps;

    @BeforeEach
    void navigate() {
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
    @MethodSource("provideProductCardInfo")
    public void validateProductCardAppearance(String productName, String description, String price) {
        productsPageSteps.validateDescriptionByProductName(productName, description);
        productsPageSteps.validatePriceByProductName(productName, price);
        productsPageSteps.validateProductByNameHasAddToCardButton(productName);
    }

    private static Stream<Arguments> provideProductCardInfo() {
        return Stream.of(
                Arguments.of("Sauce Labs Backpack",
                        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                        "$29.99"),
                Arguments.of("Sauce Labs Bolt T-Shirt",
                        "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                        "$15.99"),
                Arguments.of("Sauce Labs Onesie",
                        "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                        "$7.99"),
                Arguments.of("Sauce Labs Fleece Jacket",
                        "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                        "$49.99")
                );
    }
}
