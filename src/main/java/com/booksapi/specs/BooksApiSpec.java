package com.booksapi.specs;

import com.booksapi.config.ConfigurationManager;
import com.booksapi.config.Configuration;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class BooksApiSpec {

    public static RequestSpecification getRequestSpec() {
        Configuration config = ConfigurationManager.getConfiguration();

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(config.username());
        authScheme.setPassword(config.password());

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(config.baseUri())
                .setBasePath(config.basePath())
                .setAuth(authScheme)
                .setContentType("application/json");

        if (config.logAll()) {
            builder.addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter());
        }

        return builder.build();
    }
}