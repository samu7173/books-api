package com.project.books_api.infrastructure.gutendex;

import com.project.books_api.config.GutendexProperties;
import com.project.books_api.infrastructure.gutendex.dto.GutendexBookDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GutendexClient {

    private final RestClient restClient;
    private final GutendexProperties properties;

    public GutendexClient(
            RestClient restClient,
            GutendexProperties properties
    ) {
        this.restClient = restClient;
        this.properties = properties;
    }

    public GutendexBookDto getBook(Long id) {

        return restClient.get()
                .uri(properties.baseUrl() + "/books/" + id + "/")
                .retrieve()
                .body(GutendexBookDto.class);
    }

    public GutendexSearchResponseDto searchBooks(String query) {

        var entity = RestClient.create()
                .get()
                .uri(properties.baseUrl() + "/books/?search=" + query)
                .retrieve()
                .toEntity(String.class);

        System.out.println("STATUS: " + entity.getStatusCode());
        System.out.println("BODY: " + entity.getBody());

        return null;
    }
}
