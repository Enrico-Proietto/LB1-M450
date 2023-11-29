package ch.app.bookoasis.Tests.EntityTests;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.Role.Role;
import ch.app.bookoasis.Data.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserTest {
    private final User testUser = new User();

    @Mock
    private Book book;

    @Mock
    private Book realBook;

    private final List<Book> listOfBooks = new ArrayList<>();

    @BeforeEach
    void setUp() {
        listOfBooks.add(book);

        testUser.setId(1000L);
        testUser.setFirstName("Hans");
        testUser.setLastName("Mustermann");
        testUser.setEmail("maxmusterman@gmx.ch");
        testUser.setPassword("Test");
        testUser.setAddress("Gründenstrasse 46");
        testUser.setCity("Baselland");
        testUser.setZip("4132");
        testUser.setCountry("Schweiz");
        testUser.setPhone("+41786145278");
        testUser.setRole(Set.of(Role.USER));
        testUser.setBooksBorrowed(listOfBooks);
    }
    @Test
    void initAndGetterTest(){
        assertEquals(1000L, testUser.getId());
        assertEquals("Hans", testUser.getFirstName());
        assertEquals("Mustermann", testUser.getLastName());
        assertEquals("maxmusterman@gmx.ch", testUser.getEmail());
        assertEquals("Test", testUser.getPassword());
        assertEquals("Gründenstrasse 46", testUser.getAddress());
        assertEquals("Baselland", testUser.getCity());
        assertEquals("4132", testUser.getZip());
        assertEquals("Schweiz", testUser.getCountry());
        assertEquals("+41786145278", testUser.getPhone());
        assertEquals(Set.of(Role.USER), testUser.getRole());
        assertEquals(book, testUser.getBooksBorrowed().get(0));
    }
    @Test
    void setterTest() {
        List<Book> newBookList = new ArrayList<>();
        newBookList.add(realBook);

        testUser.setId(2000L);
        testUser.setFirstName("Test");
        testUser.setLastName("Testmann");
        testUser.setEmail("testtestmann@gmx.ch");
        testUser.setPassword("newTest");
        testUser.setAddress("Teststrasse 42");
        testUser.setCity("Testland");
        testUser.setZip("4242");
        testUser.setCountry("Testland");
        testUser.setPhone("+41781024242");
        testUser.setRole(Set.of(Role.ADMIN));
        testUser.setBooksBorrowed(newBookList);

        assertEquals(2000L, testUser.getId());
        assertEquals("Test", testUser.getFirstName());
        assertEquals("Testmann", testUser.getLastName());
        assertEquals("testtestmann@gmx.ch", testUser.getEmail());
        assertEquals("newTest", testUser.getPassword());
        assertEquals("Teststrasse 42", testUser.getAddress());
        assertEquals("Testland", testUser.getCity());
        assertEquals("4242", testUser.getZip());
        assertEquals("Testland", testUser.getCountry());
        assertEquals("+41781024242", testUser.getPhone());
        assertEquals(Set.of(Role.ADMIN), testUser.getRole());
        assertEquals(realBook, testUser.getBooksBorrowed().get(0));
    }

    @Test
    void testIllegalEmail() {
        assertThrows(IllegalArgumentException.class, () -> testUser.setEmail("neueEmail.ch"));
    }
}
