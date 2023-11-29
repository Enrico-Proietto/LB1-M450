package ch.app.bookoasis.Tests.DatabaseTests;

import ch.app.bookoasis.Data.Movie.Movie;
import ch.app.bookoasis.Service.TestMovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseMovieTest {

    @Autowired
    private TestMovieService service;

    private final String movieName = "Test Wars";
    @BeforeEach
    public void setUp() {
        List<String> listOfGenre = new ArrayList<>();
        listOfGenre.add("Adventure");
        listOfGenre.add("Horror");
        listOfGenre.add("Educatonal");

        List<String> listOfActors = new ArrayList<>();
        listOfActors.add("John Tester");
        listOfActors.add("Jeffrey-Test Williams");
        listOfActors.add("Tom Testing");

        Movie movie = new Movie();
        Long movieId = 1000L;
        movie.setId(movieId);
        movie.setTitle("Test Wars");
        movie.setDescription("In the Test Wars many test are being done to look for errors.");
        movie.setDirector("Test Johnson");
        movie.setReleaseYear(2023);
        movie.setDuration("122 min");
        movie.setRating("9/10");

        service.save(movie);
    }

    @Test
    @Order(1)
    void findMovie() {
        Movie movieForTest = service.findMovieByTitle(movieName);

        assertEquals("Test Wars", movieForTest.getTitle());
        assertEquals("Test Johnson", movieForTest.getDirector());
        service.delete(movieForTest);

    }

    @Test
    @Order(2)
    void updateBook() {
        Movie movieForTest = service.findMovieByTitle(movieName);

        movieForTest.setDescription("Test description");
        movieForTest.setDuration("60 min");
        movieForTest.setDirector("Enrico Proietto");

        service.save(movieForTest);

        Movie changedMovie = service.findMovieByTitle(movieName);

        assertEquals("Test description", changedMovie.getDescription());
        assertNotEquals("Kento Puleo", changedMovie.getDirector());

        service.delete(changedMovie);
    }

    @Test
    @Order(3)
    public void deleteMovie() {
        Movie movieForTest = service.findMovieByTitle(movieName);
        service.delete(movieForTest);
        Movie changedMovie = service.findMovieByTitle(movieName);
        assertNull(changedMovie);
    }
}
