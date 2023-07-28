package ru.itgirl.libraryproject.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.libraryproject.model.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreDto {
    private Long id;
    private String name;

    private List<BookDto> books;
 }
