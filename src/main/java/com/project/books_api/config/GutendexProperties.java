package com.project.books_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gutendex")
public record GutendexProperties(
        String baseUrl
) {
}