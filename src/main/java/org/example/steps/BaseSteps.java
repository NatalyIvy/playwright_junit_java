package org.example.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.pom.BasePage;

@Slf4j
public abstract class BaseSteps<T extends BasePage>{
    protected T ui;

    public BaseSteps(Page pwPage, Class<T> pageClass) {
        try {
            this.ui = pageClass.getDeclaredConstructor(Page.class).newInstance(pwPage);
        } catch (Exception e) {
            log.error("BasePageFactory::createInstance", e);
            throw new NullPointerException(pageClass + " instantiation failed");
        }
    }
}