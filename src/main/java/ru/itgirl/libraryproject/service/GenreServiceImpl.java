package ru.itgirl.libraryproject.service;

//import com.sun.media.sound.SF2Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.BookReducedDto;
import ru.itgirl.libraryproject.dto.GenreDto;
import ru.itgirl.libraryproject.model.Genre;
import ru.itgirl.libraryproject.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }
    private GenreDto convertToDto(Genre genre) {
        List<BookReducedDto> bookReducedDtoList = genre.getBooks()
                .stream()
                .map(book -> BookReducedDto.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors()
                                .stream()
                                .map(author -> author.getName() +  " " + author.getSurname())
                                .collect(Collectors.joining(", ")))
                        .build()
                ).collect(Collectors.toList());
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        genreDto.setBooks(bookReducedDtoList);

        return genreDto;
    }
}

