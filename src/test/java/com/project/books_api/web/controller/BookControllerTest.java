package com.project.books_api.web.controller;

import com.project.books_api.domain.entity.Author;
import com.project.books_api.domain.entity.Book;
import com.project.books_api.mapper.BookMapper;
import com.project.books_api.service.BookService;
import com.project.books_api.web.dto.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService service;

    @Mock
    private BookMapper mapper;

    @Mock
    private BookResponse bookResponse;

    private BookController controller;

    @BeforeEach
    void setUp() {
        controller = new BookController(
                service,
                mapper
        );
    }

    @Test
    void should_return_book_when_get_book_by_id_called() {

        // Given
        var book = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        when(service.getBookById(84L))
                .thenReturn(book);

        when(mapper.toResponse(book))
                .thenReturn(bookResponse);

        // When
        var response = controller.getBookById(84L);

        // Then
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .isEqualTo(bookResponse);
    }

    @Test
    void should_return_books_when_search_books_called() {

        // Given
        var book = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        when(service.searchBooks("frankenstein"))
                .thenReturn(List.of(book));

        when(mapper.toResponse(book))
                .thenReturn(bookResponse);

        // When
        var response = controller.searchBooks("frankenstein");

        // Then
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .containsExactly(bookResponse);
    }
}