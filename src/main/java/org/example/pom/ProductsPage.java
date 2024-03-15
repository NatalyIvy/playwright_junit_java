package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class ProductsPage {
    private final Page page;
    Locator filter;

    public ProductsPage(Page page) {
        Objects.requireNonNull(page, "Page should exist");
        this.page = page;
        this.filter = page.locator(".select_container");
    }

    public String getFilterSelectedOption() {
        return filter.locator(".active_option").textContent();
    }

    public List<String> getFilterOptions() {
        return filter.locator("option").allTextContents();
    }
}
