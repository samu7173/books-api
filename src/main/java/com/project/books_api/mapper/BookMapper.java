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

    Book toDomain(GutendexBookDto dto);

    List<Book> toDomain(List<GutendexBookDto> dto);

    Author toDomain(GutendexAuthorDto dto);

    BookResponse toDto(Book book);

    List<BookResponse> toDto(List<Book> books);
}
