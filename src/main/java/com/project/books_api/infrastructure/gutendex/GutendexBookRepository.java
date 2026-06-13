package com.project.books_api.infrastructure.gutendex;

import com.project.books_api.domain.entity.Book;
import com.project.books_api.mapper.BookMapper;
import com.project.books_api.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GutendexBookRepository implements BookRepository {

    private final GutendexClient gutendexClient;
    private final BookMapper mapper;

    public GutendexBookRepository(
            GutendexClient gutendexClient,
            BookMapper mapper) {
        this.gutendexClient = gutendexClient;
        this.mapper = mapper;
    }

    @Override
    public List<Book> searchBooks(String query) {

        return gutendexClient.searchBooks(query)
                .results()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Book> findById(Long id) {

        return Optional.ofNullable(
                mapper.toDomain(
                        gutendexClient.getBook(id)
                )
        );
    }
}
