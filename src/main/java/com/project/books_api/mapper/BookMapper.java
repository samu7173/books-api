package com.project.books_api.mapper;


import com.project.books_api.domain.entity.Author;
import com.project.books_api.domain.entity.Book;
import com.project.books_api.infrastructure.gutendex.dto.GutendexAuthorDto;
import com.project.books_api.infrastructure.gutendex.dto.GutendexBookDto;
import com.project.books_api.web.dto.BookResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Gutendex -> Domain
    Book toDomain(GutendexBookDto dto);

    List<Book> toDomain(List<GutendexBookDto> dtos);

    Author toDomain(GutendexAuthorDto dto);

    // Domain -> API Response
    BookResponse toResponse(Book book);

    List<BookResponse> toResponse(List<Book> books);
}