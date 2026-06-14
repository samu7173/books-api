package com.project.books_api.infrastructure.gutendex;


import com.project.books_api.domain.entity.Author;
import com.project.books_api.domain.entity.Book;
import com.project.books_api.infrastructure.gutendex.dto.GutendexAuthorDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexBookDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexSearchResponseDto;
import com.project.books_api.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GutendexBookRepositoryTest {

    @Mock
    private GutendexClient gutendexClient;

    @Mock
    private BookMapper mapper;

    private GutendexBookRepository repository;

    @BeforeEach
    void setUp() {
        repository = new GutendexBookRepository(
                gutendexClient,
                mapper
        );
    }

    @Test
    void should_return_books_when_search_books_called() {

        // Given
        var gutendexBook = new GutendexBookDto(
                84,
                "Frankenstein",
                List.of("Horror"),
                List.of(new GutendexAuthorDto("Mary Shelley"))
        );

        var domainBook = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        when(gutendexClient.searchBooks("frankenstein"))
                .thenReturn(new GutendexSearchResponseDto(
                        List.of(gutendexBook)
                ));

        when(mapper.toDomain(gutendexBook))
                .thenReturn(domainBook);

        // When
        var result = repository.searchBooks("frankenstein");

        // Then
        assertThat(result)
                .containsExactly(domainBook);
    }

    @Test
    void should_return_empty_list_when_no_books_found() {

        // Given
        when(gutendexClient.searchBooks("unknown"))
                .thenReturn(new GutendexSearchResponseDto(
                        List.of()
                ));

        // When
        var result = repository.searchBooks("unknown");

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void should_return_book_when_book_exists() {

        // Given
        var gutendexBook = new GutendexBookDto(
                84,
                "Frankenstein",
                List.of("Horror"),
                List.of(new GutendexAuthorDto("Mary Shelley"))
        );

        var domainBook = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        when(gutendexClient.getBook(84L))
                .thenReturn(gutendexBook);

        when(mapper.toDomain(gutendexBook))
                .thenReturn(domainBook);

        // When
        var result = repository.findById(84L);

        // Then
        assertThat(result)
                .contains(domainBook);
    }

    @Test
    void should_return_empty_optional_when_mapper_returns_null() {

        // Given
        var gutendexBook = new GutendexBookDto(
                84,
                "Frankenstein",
                List.of("Horror"),
                List.of(new GutendexAuthorDto("Mary Shelley"))
        );

        when(gutendexClient.getBook(84L))
                .thenReturn(gutendexBook);

        when(mapper.toDomain(gutendexBook))
                .thenReturn(null);

        // When
        var result = repository.findById(84L);

        // Then
        assertThat(result).isEmpty();
    }
}
