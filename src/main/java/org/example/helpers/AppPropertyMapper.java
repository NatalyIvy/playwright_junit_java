package org.example.helpers;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/application.properties")
public interface AppPropertyMapper extends Config {

    @Key("base_url")
    @DefaultValue("https://www.saucedemo.com")
    String getBaseUrl();

    @Key("standard_user_name")
    String getStandardUsername();

    @Key("standard_user_password")
    String getStandardPassword();
}