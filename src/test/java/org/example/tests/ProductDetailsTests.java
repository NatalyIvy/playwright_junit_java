package org.example.tests;

import org.example.config.TestBase;
import org.example.steps.LoginPageSteps;
import org.example.steps.ProductDetailsPageSteps;
import org.example.steps.ProductsPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Product Details page features")
public class ProductDetailsTests extends TestBase {
  LoginPageSteps loginPageSteps;
  ProductsPageSteps productsPageSteps;
  ProductDetailsPageSteps productDetailsPageSteps;

  private static final String PRODUCTS_PAGE_URL = "https://www.saucedemo.com/inventory.html";

  @BeforeEach
  void loginAsStandardUser() {
    loginPageSteps = new LoginPageSteps(page);
    loginPageSteps.openUrlAndLoginAsStandardUser();
    productsPageSteps = new ProductsPageSteps(page);
  }

  @Test
  @DisplayName("Validate Product details web elements for each product")
  void validateProductDetailsItemsForEachProduct() {
    int productsCount = productsPageSteps.ui.getBlockTitle().all().size();
    for (int i = 0; i < productsCount; i++) {
      productsPageSteps.clickOnProductByIndex(i);
      productDetailsPageSteps = new ProductDetailsPageSteps(page);
      productDetailsPageSteps.validateProductsDetailsPageAppearance();
      productDetailsPageSteps.clickOnBackArrow();
      page.waitForURL(PRODUCTS_PAGE_URL);
    }
  }

  @ParameterizedTest
  @DisplayName("Validate product details text values for each product")
  @MethodSource("org.example.test_data.ProductDetailsData#provideProductCardInfo")
  void validateProductDetailsValues(String productName, String description, String price) {
    productsPageSteps.clickOnProductByName(productName);
    productDetailsPageSteps = new ProductDetailsPageSteps(page);
    productDetailsPageSteps.validateTitle(productName);
    productDetailsPageSteps.validateDescription(description);
    productDetailsPageSteps.validatePrice(price);
    productDetailsPageSteps.validateAddToCartButton("Add to cart");
    productDetailsPageSteps.validateBackArrow("Back to products");
  }
}
