package ch.app.bookoasis.Repo;

import ch.app.bookoasis.Data.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMovieRepo extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);
    Movie findMovieByTitle(String title);
}
