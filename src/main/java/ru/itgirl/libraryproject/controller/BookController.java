package ru.itgirl.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @GetMapping("/book/{id}")
    BookDto getBookById(@PathVariable("id") Long id){
        return bookService.getBookById(id);
    }
    @GetMapping("/book")
    BookDto getBookByName(@RequestParam("name") String name) {
        return bookService.getByNameV1(name);
    }
}