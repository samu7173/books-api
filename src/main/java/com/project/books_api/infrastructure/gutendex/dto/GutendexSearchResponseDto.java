package com.project.books_api.infrastructure.gutendex.dto;

import java.util.List;

public record GutendexSearchResponseDto(
        List<GutendexBookDto> results
) {
}