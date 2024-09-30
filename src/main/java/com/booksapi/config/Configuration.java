package com.booksapi.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"
})
public interface Configuration extends Config {

    @Key("api.baseUri")
    String baseUri();

    @Key("api.basePath")
    String basePath();

    @Key("api.username")
    String username();

    @Key("api.password")
    String password();

    @Key("log.all")
    @DefaultValue("true")
    boolean logAll();
}