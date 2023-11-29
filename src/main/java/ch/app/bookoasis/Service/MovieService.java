package ch.app.bookoasis.Service;

import ch.app.bookoasis.Data.Movie.Movie;
import ch.app.bookoasis.Repo.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends AbstractService<Movie> {
    private final MovieRepo repo;
    public MovieService(MovieRepo repo) {
        super(repo);
        this.repo = repo;
    }
    public Movie findMovieById(Long id) { return repo.findMovieById(id); }
    public Movie findMovieByTitle(String title) { return repo.findMovieByTitle(title); }
    public List<Movie> findMoviesByDirector(String director) { return repo.findMoviesByDirector(director); }
    public List<Movie> findAll() { return repo.findAll(); }
}
