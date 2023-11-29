package ch.app.bookoasis.Service;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Repo.TestBookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestBookService extends AbstractService<Book>{
    private final TestBookRepo repo;
    public TestBookService(TestBookRepo repo){
        super(repo);
        this.repo = repo;
    }

    public Book findBookById(Long id) { return repo.findBookById(id); }
    public Book findBookByIsbn(String isbn) { return repo.findBookByIsbn(isbn); }
    public List<Book> findBooksByAuthor(String author) { return repo.findBooksByAuthor(author); }
    public List<Book> findBooksByPublisher(String publisher) { return repo.findBooksByPublisher(publisher); }

}
