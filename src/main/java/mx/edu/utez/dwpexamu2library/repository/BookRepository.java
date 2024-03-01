package mx.edu.utez.dwpexamu2library.repository;

import mx.edu.utez.dwpexamu2library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {



}
