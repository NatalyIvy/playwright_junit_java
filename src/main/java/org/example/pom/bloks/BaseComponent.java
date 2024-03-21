package org.example.pom.bloks;

import com.microsoft.playwright.Locator;

public abstract class BaseComponent {
    public Locator root;

    public BaseComponent(Locator locator) {
        this.root = locator;
    }
}