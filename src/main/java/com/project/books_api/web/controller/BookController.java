package com.project.books_api.web.controller;

import com.project.books_api.domain.entity.Book;
import com.project.books_api.mapper.BookMapper;
import com.project.books_api.service.BookService;
import com.project.books_api.web.api.BooksApi;
import com.project.books_api.web.dto.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(
            BookService service,
            BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<BookResponse> getBookById(Long id) {

        Book book = service.getBookById(id);

        return ResponseEntity.ok(
                mapper.toResponse(book)
        );
    }

    @Override
    public ResponseEntity<List<BookResponse>> searchBooks(String query) {

        List<BookResponse> books = service.searchBooks(query)
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(books);
    }
}
