package org.example.steps;

import com.epam.reportportal.annotations.Step;
import com.microsoft.playwright.Page;
import org.example.helpers.annotations.CustomStep;
import org.example.pom.ProductsPage;
import org.example.pom.bloks.ProductCard;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageSteps {
    public final ProductsPage ui;

    public ProductsPageSteps(Page pwPage) {
        this.ui = new ProductsPage(pwPage);
    }

    static final String ADD_TO_CART_BUTTON_NAME = "Add to cart";

    @CustomStep("Validate on Products page the filter selected option is [{option}]")
    public void validateFilterSelectedOption(String option) {
        assertThat(ui.getFilterSelectedOption()).isEqualTo(option);
    }

    @CustomStep("Validate on Products page the filter options are [{options}]")
    public void validateFilterOptions(List<String> options) {
        assertThat(ui.getFilterOptions())
                .as("Options should be " + options)
                .isEqualTo(options);
    }

    @CustomStep("Open products filter")
    public void clickOnFilter() {
        ui.getFilter().click();
    }

    @CustomStep("Validate product [{productName}] has description [{description}]")
    public void validateDescriptionByProductName(String productName, String description) {
        ProductCard productBlock = ui.getProductBlockByName(productName);
        assertThat(productBlock.getDescription()).isEqualTo(description);
    }

    @CustomStep("Validate product [{productName}] has price [{price}]")
    public void validatePriceByProductName(String productName, String price) {
        ProductCard productBlock = ui.getProductBlockByName(productName);
        assertThat(productBlock.getPrice()).isEqualTo(price);
    }

    @CustomStep("Validate product [{productName}] has Add to cart button")
    public void validateProductByNameHasAddToCardButton(String productName) {
        ProductCard productCard = ui.getProductBlockByName(productName);
        assertThat(productCard.getAddToCartButton().textContent()).isEqualTo(ADD_TO_CART_BUTTON_NAME);
    }

    @CustomStep("Click on product by name [{productName}]")
    public void clickOnProductByName(String productName) {
        ProductCard productCard = ui.getProductBlockByName(productName);
        productCard.getProductName().click();
    }

    @CustomStep("Click on product by index [{index}]")
    public void clickOnProductByIndex(int index) {
        ui.clickOnBlockTitleByIndex(index);
    }
}