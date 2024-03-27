package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class ProductDetailsPage extends BasePage {

    Locator title = pw.locator("[data-test='inventory-item-name']");
    Locator description = pw.locator("[data-test='inventory-item-desc']");
    Locator price = pw.locator("[data-test='inventory-item-price']");
    Locator addToCartButton = pw.locator("[data-test='add-to-cart']");
    Locator image = pw.locator("[class='inventory_details_img']");
    Locator backArrow = pw.locator("[data-test='back-to-products']");

    public ProductDetailsPage(Page pw) {
        super(pw);
    }
}