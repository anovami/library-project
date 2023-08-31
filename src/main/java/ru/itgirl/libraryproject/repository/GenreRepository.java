package ru.itgirl.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.model.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    Optional<Genre> findById(Long aLong);
}
