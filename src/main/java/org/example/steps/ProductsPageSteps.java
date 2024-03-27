package org.example.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
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

    @Step("Validate on Products page the filter selected option is [{option}]")
    public void validateFilterSelectedOption(String option) {
        assertThat(ui.getFilterSelectedOption()).isEqualTo(option);
    }

    @Step("Validate on Products page the filter options are [{options}]")
    public void validateFilterOptions(List<String> options) {
        assertThat(ui.getFilterOptions()).isEqualTo(options);
    }

    @Step("Open products filter")
    public void clickOnFilter() {
        ui.getFilter().click();
    }

    @Step("Validate product [{productName}] has description [{description}]")
    public void validateDescriptionByProductName(String productName, String description) {
        ProductCard productBlock = ui.getProductBlockByName(productName);
        assertThat(productBlock.getDescription()).isEqualTo(description);
    }

    @Step("Validate product [{productName}] has price [{price}]")
    public void validatePriceByProductName(String productName, String price) {
        ProductCard productBlock = ui.getProductBlockByName(productName);
        assertThat(productBlock.getPrice()).isEqualTo(price);
    }

    @Step("Validate product [{productName}] has Add to cart button")
    public void validateProductByNameHasAddToCardButton(String productName) {
        ProductCard productCard = ui.getProductBlockByName(productName);
        assertThat(productCard.getAddToCartButton().textContent()).isEqualTo(ADD_TO_CART_BUTTON_NAME);
    }

    @Step("Click on product by name [{productName}]")
    public void clickOnProductByName(String productName) {
        ProductCard productCard = ui.getProductBlockByName(productName);
        productCard.getProductName().click();
    }

    @Step("Click on product by index [{index}]")
    public void clickOnProductByIndex(int index) {
        ui.clickOnBlockTitleByIndex(index);
    }
}