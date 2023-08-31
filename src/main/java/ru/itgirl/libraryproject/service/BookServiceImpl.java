package ru.itgirl.libraryproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.BookCreateDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.BookUpdateDto;
import ru.itgirl.libraryproject.model.Book;
import ru.itgirl.libraryproject.model.Genre;
import ru.itgirl.libraryproject.repository.BookRepository;
import ru.itgirl.libraryproject.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDto(book);
    }

    private BookDto convertToDto(Book book) {
        List<AuthorDto> authorDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()
                ).toList();
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(authorDtoList)
                .build();
    }

    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

//    private BookDto convertEntityToDto(Book book) {
//        return BookDto.builder()
//                .id(book.getId())
//                .genre(book.getGenre().getName())
//                .name(book.getName())
//                .build();
//    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        Genre genre = genreRepository.findById(bookCreateDto.getGenre_id()).orElseThrow(() -> new RuntimeException());
        Book book = new Book();
        book.setName(bookCreateDto.getName());
        book.setGenre_id(bookCreateDto.getGenre_id());
        bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(book);
//        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
//        BookDto bookDto = convertEntityToDto(book);
        return bookDto;
    }
    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow(() -> new RuntimeException("Не такой книги"));
        book.setName(bookUpdateDto.getName());
        book.setGenre_id(bookUpdateDto.getGenre_id());
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(savedBook);
        return bookDto;
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre_id(bookCreateDto.getGenre_id())
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        List<AuthorDto> authorDtoList = null;
        if (book.getAuthors() != null) {
            authorDtoList = book.getAuthors()
                    .stream()
                    .map(author -> AuthorDto.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .surname(author.getSurname())
                            .build())
                    .toList();
        }
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(authorDtoList)
                .build();
        return bookDto;
    }
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}