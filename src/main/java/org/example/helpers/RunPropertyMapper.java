package org.example.helpers;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:run.properties"})
public interface RunPropertyMapper extends Config {

    @Key("browser_type")
    @DefaultValue("chromium")
    String getBrowserType();

    @Key("headless_mode")
    @DefaultValue("false")
    boolean getHeadlessMode();
}