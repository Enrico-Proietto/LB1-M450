package ch.app.bookoasis.Tests.EntityTests;

import ch.app.bookoasis.Data.Movie.Movie;
import ch.app.bookoasis.Data.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MovieTest {
    private final Movie testMovie = new Movie();
    @Mock
    private User user;
    @Mock
    private User realUser;
    private final List<User> userThatBorrowedMovie = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userThatBorrowedMovie.add(user);

        testMovie.setId(1000L);
        testMovie.setTitle("Testing with Enrico & Kento");
        testMovie.setDescription("A Movie where Enrico Proietto & Kento Puleo shows how to use JUnit in Java.");
        testMovie.setDirector("Enrico Proietto");
        testMovie.setReleaseYear(2023);
        testMovie.setDuration("120 min");
        testMovie.setRating("10/10");
        testMovie.setListOfUsers(userThatBorrowedMovie);
    }

    @Test
    void initAndGetterTest() {
        assertEquals(1000L, testMovie.getId());
        assertEquals("Testing with Enrico & Kento", testMovie.getTitle());
        assertEquals("A Movie where Enrico Proietto & Kento Puleo shows how to use JUnit in Java.", testMovie.getDescription());
        assertEquals("Enrico Proietto", testMovie.getDirector());
        assertEquals(2023, testMovie.getReleaseYear());
        assertEquals("120 min", testMovie.getDuration());
        assertEquals("10/10", testMovie.getRating());
        assertEquals(user, testMovie.getListOfUsers().get(0));
    }

    @Test
    void setterTest() {
        List<User> newUserList = new ArrayList<>();
        newUserList.add(realUser);

        testMovie.setId(2000L);
        testMovie.setTitle("Testing");
        testMovie.setDescription("A Movie about JUnit in Java.");
        testMovie.setDirector("Tester Testington");
        testMovie.setReleaseYear(2000);
        testMovie.setDuration("400 min");
        testMovie.setRating("5/10");
        testMovie.setListOfUsers(newUserList);

        assertEquals(2000L, testMovie.getId());
        assertEquals("Testing", testMovie.getTitle());
        assertEquals("A Movie about JUnit in Java.", testMovie.getDescription());
        assertEquals("Tester Testington", testMovie.getDirector());
        assertEquals(2000, testMovie.getReleaseYear());
        assertEquals("400 min", testMovie.getDuration());
        assertEquals("5/10", testMovie.getRating());
        assertEquals(realUser, testMovie.getListOfUsers().get(0));
    }

    @Test
    void testIllegalDuration() { assertThrows(IllegalArgumentException.class, () -> testMovie.setDuration("200")); }
}
