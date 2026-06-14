package com.project.books_api.infrastructure.gutendex;

import com.project.books_api.config.GutendexProperties;
import com.project.books_api.infrastructure.gutendex.dto.GutendexAuthorDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexBookDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexSearchResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GutendexClientTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Mock
    private GutendexProperties properties;

    private GutendexClient client;

    @BeforeEach
    void setUp() {
        client = new GutendexClient(restClient, properties);
    }

    @Test
    void should_return_book_when_get_book_called() {

        // Given
        var expectedBook = new GutendexBookDto(
                84,
                "Frankenstein",
                List.of("Horror"),
                List.of(new GutendexAuthorDto("Mary Shelley"))
        );

        when(properties.baseUrl())
                .thenReturn("https://gutendex.com");

        when(restClient.get())
                .thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri("https://gutendex.com/books/84/"))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.body(GutendexBookDto.class))
                .thenReturn(expectedBook);

        // When
        var result = client.getBook(84L);

        // Then
        assertThat(result).isEqualTo(expectedBook);
    }

    @Test
    void should_return_books_when_search_books_called() {

        // Given
        var response = new GutendexSearchResponseDto(
                List.of(
                        new GutendexBookDto(
                                84,
                                "Frankenstein",
                                List.of("Horror"),
                                List.of(new GutendexAuthorDto("Mary Shelley"))
                        )
                )
        );

        when(properties.baseUrl())
                .thenReturn("https://gutendex.com");

        when(restClient.get())
                .thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(
                "https://gutendex.com/books/?search=frankenstein"))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.body(GutendexSearchResponseDto.class))
                .thenReturn(response);

        // When
        var result = client.searchBooks("frankenstein");

        // Then
        assertThat(result).isEqualTo(response);
    }
}
