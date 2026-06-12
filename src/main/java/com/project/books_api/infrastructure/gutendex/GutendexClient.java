package com.project.books_api.infrastructure.gutendex;

import com.project.books_api.config.GutendexProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GutendexClient {

    private final RestClient restClient;
    private final GutendexProperties properties;

}
