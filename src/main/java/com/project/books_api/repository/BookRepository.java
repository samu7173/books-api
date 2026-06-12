package com.project.books_api.repository;

import com.project.books_api.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> searchBooks(String query);

    Optional<Book> findById(Long id);
}
