package ch.app.bookoasis.Tests.DatabaseTests;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Service.TestBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Year;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseBookTest {
    @Autowired
    private TestBookService service;
    private final String isbn = "978-1-234567-89-1";

    @BeforeEach
    public void init() {

        Book testbook = new Book();

        testbook.setIsbn("978-1-234567-89-1");
        testbook.setTitle("Test with jUnit");
        testbook.setAuthor("Enrico & Kento");
        testbook.setPublisher("Proietto Company");
        testbook.setDescription("A test book for auto testing in JUnit");
        testbook.setPages(420);
        testbook.setReleaseYear(2023);
        testbook.setInStock(3);
        testbook.setBorrowed(2);

        service.save(testbook);
    }

    @Test
    public void findBook() {
        Book bookForTests = service.findBookByIsbn(isbn);

        assertTrue(bookForTests.getReleaseYear() > 1600);
        assertTrue(bookForTests.getReleaseYear() < Year.now().getValue()+1);

        assertEquals("978-1-234567-89-1", bookForTests.getIsbn());
        assertEquals("Test with jUnit", bookForTests.getTitle());
        assertEquals("Enrico & Kento", bookForTests.getAuthor());
        assertEquals("Proietto Company", bookForTests.getPublisher());
        assertEquals("A test book for auto testing in JUnit", bookForTests.getDescription());
        assertNotEquals(5, bookForTests.getPages());
        assertEquals(2023, bookForTests.getReleaseYear());
        service.delete(bookForTests);
    }

    @Test
    public void updateBook() {
        Book bookForTests = service.findBookByIsbn(isbn);

        bookForTests.setTitle("Test for change");
        bookForTests.setAuthor("Test for change");
        bookForTests.setPublisher("Test for change");
        bookForTests.setDescription("Test for change");
        bookForTests.setPages(42);
        bookForTests.setReleaseYear(42);

        service.save(bookForTests);

        Book changedBook = service.findBookByIsbn(isbn);

        assertEquals("Test for change", changedBook.getTitle());
        assertTrue(changedBook.getPages() > 0);
        assertFalse(changedBook.getReleaseYear() < 5);
        service.delete(bookForTests);
    }

    @Test
    public void deleteBook() {
        Book bookForTest = service.findBookByIsbn(isbn);
        service.delete(bookForTest);
        Book changedBook = service.findBookByIsbn(isbn);
        assertNull(changedBook);
    }
}
