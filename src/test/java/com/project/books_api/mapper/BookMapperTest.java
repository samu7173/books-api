package com.project.books_api.mapper;

import com.project.books_api.domain.entity.Author;
import com.project.books_api.domain.entity.Book;
import com.project.books_api.infrastructure.gutendex.dto.GutendexAuthorDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexBookDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void should_map_gutendex_book_to_domain() {

        // Given
        var dto = new GutendexBookDto(
                84,
                "Frankenstein",
                List.of("Horror"),
                List.of(new GutendexAuthorDto("Mary Shelley"))
        );

        // When
        var result = mapper.toDomain(dto);

        // Then
        assertThat(result.id()).isEqualTo(84L);
        assertThat(result.title()).isEqualTo("Frankenstein");
        assertThat(result.subjects()).containsExactly("Horror");
        assertThat(result.authors())
                .extracting(Author::name)
                .containsExactly("Mary Shelley");
    }

    @Test
    void should_map_author_to_domain() {

        // Given
        var dto = new GutendexAuthorDto("Mary Shelley");

        // When
        var result = mapper.toDomain(dto);

        // Then
        assertThat(result.name()).isEqualTo("Mary Shelley");
    }

    @Test
    void should_map_domain_book_to_response() {

        // Given
        var book = new Book(
                84L,
                "Frankenstein",
                List.of("Horror"),
                List.of(new Author("Mary Shelley"))
        );

        // When
        var result = mapper.toResponse(book);

        // Then
        assertThat(result.getId()).isEqualTo(84L);
        assertThat(result.getTitle()).isEqualTo("Frankenstein");
        assertThat(result.getSubjects()).containsExactly("Horror");
    }
}