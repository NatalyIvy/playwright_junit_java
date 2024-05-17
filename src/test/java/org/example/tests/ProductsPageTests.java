package org.example.tests;

import java.util.List;
import org.example.config.TestBase;
import org.example.steps.LoginPageSteps;
import org.example.steps.ProductsPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

  @DisplayName("Validate products filter dropdown appearance")
  @Tag("MYPROJ-000")
  @Test
  public void validateProductsFilter() {
    List<String> expectedOptions =
        List.of("Name (A to Z)", "Name (Z to 7)", "Price (low to high)", "Price (high to low)");

    productsPageSteps.validateFilterSelectedOption("Name (A to Z)");
    productsPageSteps.clickOnFilter();
    productsPageSteps.validateFilterOptions(expectedOptions);
  }

  @DisplayName("Validate product card appearance")
  @Tag("MYPROJ-123")
  @ParameterizedTest
  @MethodSource("org.example.test_data.ProductDetailsData#provideProductCardInfo")
  public void validateProductCardAppearance(String productName, String description, String price) {
    productsPageSteps.validateDescriptionByProductName(productName, description);
    productsPageSteps.validatePriceByProductName(productName, price);
    productsPageSteps.validateProductByNameHasAddToCardButton(productName);
  }
}
