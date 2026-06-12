package com.project.books_api.domain.entity;

import java.util.List;

public record Book(
        Long id,
        String title,
        List<String> subjects,
        List<Author> authors
) {
}
