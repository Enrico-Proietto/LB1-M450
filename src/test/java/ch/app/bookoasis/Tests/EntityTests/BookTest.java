package ch.app.bookoasis.Tests.EntityTests;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookTest {
    private final Book testBook = new Book();
    @Mock
    private User user;
    @Mock
    private User realUser;
    private final List<User> userThatBorrowedBook = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userThatBorrowedBook.add(user);

        testBook.setId(1000L);
        testBook.setIsbn("978-1-234567-89-1");
        testBook.setTitle("Test with JUnit");
        testBook.setAuthor("Enrico & Kento");
        testBook.setPublisher("Proietto Company");
        testBook.setDescription("A test book for auto testing in JUnit");
        testBook.setPages(420);
        testBook.setReleaseYear(2023);
        testBook.setInStock(5);
        testBook.setBorrowed(0);
        testBook.setListOfUsers(userThatBorrowedBook);
    }

    @Test
    void initAndGetterTest() {
        assertEquals(1000L, testBook.getId());
        assertEquals("978-1-234567-89-1", testBook.getIsbn());
        assertEquals("Test with JUnit", testBook.getTitle());
        assertEquals("Enrico & Kento", testBook.getAuthor());
        assertEquals("Proietto Company", testBook.getPublisher());
        assertEquals("A test book for auto testing in JUnit", testBook.getDescription());
        assertEquals(420, testBook.getPages());
        assertEquals(2023, testBook.getReleaseYear());
        assertEquals(5, testBook.getInStock());
        assertEquals(0, testBook.getBorrowed());
        assertEquals(user, testBook.getListOfUsers().get(0));
    }

    @Test
    void setterTest() {
        List<User> newUserList = new ArrayList<>();
        newUserList.add(realUser);

        testBook.setId(2000L);
        testBook.setIsbn("978-6-543210-12-3");
        testBook.setTitle("Testing with JUnit");
        testBook.setAuthor("Enrico");
        testBook.setPublisher("Proietto & co");
        testBook.setDescription("A test that is currently done.");
        testBook.setPages(840);
        testBook.setReleaseYear(2000);
        testBook.setInStock(0);
        testBook.setBorrowed(5);
        testBook.setListOfUsers(newUserList);

        assertEquals(2000L, testBook.getId());
        assertEquals("978-6-543210-12-3", testBook.getIsbn());
        assertEquals("Testing with JUnit", testBook.getTitle());
        assertEquals("Enrico", testBook.getAuthor());
        assertEquals("Proietto & co", testBook.getPublisher());
        assertEquals("A test that is currently done.", testBook.getDescription());
        assertEquals(840, testBook.getPages());
        assertEquals(2000, testBook.getReleaseYear());
        assertEquals(0, testBook.getInStock());
        assertEquals(5, testBook.getBorrowed());
        assertEquals(realUser, testBook.getListOfUsers().get(0));
    }

    @Test
    void testIllegalIsbn() { assertThrows(IllegalArgumentException.class, () -> testBook.setIsbn("9786543210123")); }
}
