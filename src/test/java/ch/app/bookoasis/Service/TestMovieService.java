package ch.app.bookoasis.Service;

import ch.app.bookoasis.Data.Movie.Movie;
import ch.app.bookoasis.Repo.TestMovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMovieService extends AbstractService<Movie>{
    private final TestMovieRepo repo;
    public TestMovieService(TestMovieRepo repo) {
        super(repo);
        this.repo = repo;
    }
    public Movie findMovieById(Long id) { return repo.findMovieById(id); }
    public Movie findMovieByTitle(String title) { return repo.findMovieByTitle(title); }
}
