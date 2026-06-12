package com.project.books_api.infrastructure.gutendex;

import com.project.books_api.domain.entity.Book;
import com.project.books_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GutendexBookRepository implements BookRepository {

    @Override
    public List<Book> searchBooks(String query) {
        return List.of();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }
}
