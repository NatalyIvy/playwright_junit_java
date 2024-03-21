package org.example.pom;

import com.microsoft.playwright.Page;

public abstract class BasePage {
    public Page pw;

    public BasePage(Page pw){
        this.pw = pw;
    }
}