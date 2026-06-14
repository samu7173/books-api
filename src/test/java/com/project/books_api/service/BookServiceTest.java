package com.project.books_api.service;

import com.project.books_api.domain.entity.Author;
import com.project.books_api.domain.entity.Book;
import com.project.books_api.repository.BookRepository;
import com.project.books_api.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    private BookServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BookServiceImpl(repository);
    }

    @Test
    void should_return_books_when_search_books_called() {

        // Given
        var expectedBooks = List.of(
                new Book(
                        84L,
                        "Frankenstein",
                        List.of("Horror"),
                        List.of(new Author("Mary Shelley"))
                )
        );

        when(repository.searchBooks("frankenstein"))
                .thenReturn(expectedBooks);

        // When
        var result = service.searchBooks("frankenstein");

        // Then
        assertThat(result).isEqualTo(expectedBooks);
    }

    @Test
    void should_return_book_when_book_exists() {

        // Given
        var expectedBook = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        when(repository.findById(84L))
                .thenReturn(Optional.of(expectedBook));

        // When
        var result = service.getBookById(84L);

        // Then
        assertThat(result).isEqualTo(expectedBook);
    }

    @Test
    void should_throw_exception_when_book_not_exists() {

        // Given
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> service.getBookById(999L))
                .isInstanceOf(NoSuchElementException.class);
    }
}