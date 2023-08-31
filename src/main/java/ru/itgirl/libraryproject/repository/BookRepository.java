package ru.itgirl.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject.model.Book;
import java.util.Optional;
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
}


