package org.example.pom.bloks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class ProductCard extends BaseComponent {

    Locator descriptionLocator = root.locator(".inventory_item_desc");
    Locator priceLocator = root.locator(".inventory_item_price");
    Locator addToCartButton = root.getByRole(AriaRole.BUTTON);

    public ProductCard(Locator locator) {
        super(locator);
    }

    public String getDescription() {
        return this.descriptionLocator.textContent();
    }

    public String getPrice() {
        return this.priceLocator.textContent();
    }
}