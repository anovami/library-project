package ru.itgirl.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject.dto.BookCreateDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.BookUpdateDto;
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
    @PostMapping("/book/create")
    BookDto createBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/book/update")
    BookDto updateBookDto(@RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }
    @DeleteMapping("/book/delete/{id}")
    void updateBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}