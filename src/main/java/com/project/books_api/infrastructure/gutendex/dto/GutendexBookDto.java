package com.project.books_api.infrastructure.gutendex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GutendexBookDto(
        Integer id,
        String title,
        List<String> subjects,
        List<GutendexAuthorDto> authors
) {
}
