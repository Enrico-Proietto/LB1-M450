package ch.app.bookoasis.Data.Movie;

import ch.app.bookoasis.Data.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String director;
    @NotNull
    private int releaseYear;
    @NotNull
    private String duration;
    @NotNull
    private String rating;
    private String pictureUrl;
    @ManyToMany
    private List<User> listOfUsers;

    public void setDuration(String duration) {
        if (!duration.contains("min")){
            throw new IllegalArgumentException("Duration needs to be saved in minutes");
        }
        else {
            this.duration = duration;
        }
    }
}
