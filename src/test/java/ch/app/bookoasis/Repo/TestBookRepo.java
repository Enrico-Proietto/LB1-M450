package ch.app.bookoasis.Repo;

import ch.app.bookoasis.Data.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestBookRepo extends JpaRepository<Book, Long> {
    Book findBookById(Long id);
    Book findBookByIsbn(String isbn);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByPublisher(String publisher);
}
