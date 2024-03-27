package org.example.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.LocatorAssertions.HasTextOptions;
import io.qameta.allure.Step;
import org.example.pom.ProductDetailsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductDetailsPageSteps {
    public final ProductDetailsPage ui;

    public ProductDetailsPageSteps(Page pw) {
        this.ui = new ProductDetailsPage(pw);
    }

    @Step("Validate Products Details page appearance")
    public void validateProductsDetailsPageAppearance() {
        assertThat(ui.getTitle()).isVisible();
        assertThat(ui.getPrice()).isVisible();
        assertThat(ui.getImage()).isVisible();
        assertThat(ui.getDescription()).isVisible();
        assertThat(ui.getBackArrow()).isVisible();
        assertThat(ui.getAddToCartButton()).isVisible();
    }

    @Step("Click on Back to products page")
    public void clickOnBackArrow() {
        ui.getBackArrow().click();
    }

    @Step("Validate on Product Details page the title is [{title}]")
    public void validateTitle(String title) {
        assertThat(ui.getTitle()).containsText(title, new LocatorAssertions.ContainsTextOptions().setTimeout(5000));
    }

    @Step("Validate on Product Details page the description is [{description}]")
    public void validateDescription(String description) {
        assertThat(ui.getDescription()).hasText(description, new HasTextOptions().setIgnoreCase(false));
    }

    @Step("Validate on Product Details page the back arrow has label [{backArrow}]")
    public void validateBackArrow(String backArrow) {
        assertThat(ui.getBackArrow()).containsText(backArrow);
    }

    @Step("Validate on Product Details page the price is [{price}]")
    public void validatePrice(String price) {
        assertThat(ui.getPrice()).hasText(price);
    }

    @Step("Validate on Product Details page the add to cart button has text [{addToCartButton}]")
    public void validateAddToCartButton(String addToCartButton) {
        assertThat(ui.getAddToCartButton()).containsText(addToCartButton);
    }
}