package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.BookDto;

public interface BookService {
    BookDto getBookById(Long id);
    BookDto getByNameV1(String name);
}
