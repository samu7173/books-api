package com.project.books_api.service;

import com.project.books_api.domain.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> searchBooks(String query);

    Book getBookById(Long id);
}
