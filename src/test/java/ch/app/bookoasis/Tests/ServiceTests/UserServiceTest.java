package ch.app.bookoasis.Tests.ServiceTests;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.Role.Role;
import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Repo.TestUserRepo;
import ch.app.bookoasis.Service.TestUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private TestUserRepo testUserRepo;
    @Mock
    private Book book;
    private final String email = "maxmusterman@gmx.ch";
    private final List<Book> listOfBooks = new ArrayList<>();

    //A User can be found by his Id.
    @Test
    void findUserByIdMockTest() {
        Long id = 1000L;
        User userForTest = new User(id, "Hans", "Mustermann", "maxmusterman@gmx.ch", "Test", "Gründenstrasse 46",
                "Baselland", "4132", "CH", "0615529595", Set.of(Role.USER), listOfBooks);

        when(testUserRepo.findUserById(id)).thenReturn(userForTest);

        User userForMockTest = testUserRepo.findUserById(id);

        assertEquals("Hans", userForMockTest.getFirstName());
        assertEquals("Mustermann", userForMockTest.getLastName());
        assertTrue(userForMockTest.getEmail().contains("@"));
    }
    // A user can be found with his email. Because it is unique I test, if I can find him with the Service.
    @Test
    void findUserByEmailMockTest() {
        User userForTest = new User(1000L, "Hans", "Mustermann", "maxmusterman@gmx.ch", "Test", "Gründenstrasse 46",
                                "Baselland", "4132", "CH", "0615529595", Set.of(Role.USER), listOfBooks);

        when(testUserRepo.findUserByEmail(email)).thenReturn(userForTest);

        User userForMockTest = testUserRepo.findUserByEmail(email);

        assertEquals("Hans", userForMockTest.getFirstName());
        assertEquals("Mustermann", userForMockTest.getLastName());
        assertTrue(userForMockTest.getEmail().contains("@"));
    }
    // Also should every User have a phone, thats why I can search him with his phone number.
    @Test
    void findUserByPhoneMockTestSuccess() {
        String phoneNumber = "0615529595";
        User userForTest = new User(1000L, "Hans", "Mustermann", "maxmusterman@gmx.ch", "Test", "Gründenstrasse 46",
                "Baselland", "4132", "CH", "0615529595", Set.of(Role.USER), listOfBooks);

        when(testUserRepo.findUserByPhone(phoneNumber)).thenReturn(userForTest);

        User userForMockTest = testUserRepo.findUserByPhone(phoneNumber);

        assertEquals("Hans", userForMockTest.getFirstName());
        assertEquals("Mustermann", userForMockTest.getLastName());
        assertTrue(userForMockTest.getEmail().contains("@"));
    }

    @Test
    void userBorrowsBooks() {
        List<Book> listWithoutBooks = new ArrayList<>();
        List<Book> listWithSomeBooks = new ArrayList<>();

        Book lordOfTheRings = book;
        Book frankenstein = book;
        Book dracula = book;

        listWithSomeBooks.add(lordOfTheRings);
        listWithSomeBooks.add(frankenstein);
        listWithSomeBooks.add(dracula);

        Long id = 1000L;
        User userForTest = new User(id, "Hans", "Mustermann", "maxmusterman@gmx.ch", "Test", "Gründenstrasse 46",
                "Baselland", "4132", "CH", "0615529595", Set.of(Role.USER), listWithoutBooks);

        userForTest.setBooksBorrowed(listWithSomeBooks);

        assertEquals(lordOfTheRings, userForTest.getBooksBorrowed().get(0));
        assertEquals(lordOfTheRings, userForTest.getBooksBorrowed().get(1));
        assertEquals(lordOfTheRings, userForTest.getBooksBorrowed().get(2));
    }
}
