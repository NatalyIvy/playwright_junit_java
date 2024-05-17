package org.example.helpers.env;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/env/${environment}/application.properties"})
public interface AppPropertyMapper extends Config {

    @Key("base_url")
    String getBaseUrl();

    @Key("standard_user_name")
    String getStandardUsername();

    @Key("standard_user_password")
    String getStandardPassword();
}