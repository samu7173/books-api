package com.project.books_api.service.impl;

import com.project.books_api.domain.entity.Book;
import com.project.books_api.repository.BookRepository;
import com.project.books_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> searchBooks(String query) {
        return repository.searchBooks(query);
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }
}
