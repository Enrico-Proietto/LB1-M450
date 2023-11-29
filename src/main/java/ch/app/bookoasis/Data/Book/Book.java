package ch.app.bookoasis.Data.Book;

import ch.app.bookoasis.Data.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String isbn;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private String description;
    @NotNull
    private int pages;
    @NotNull
    private int releaseYear;
    private String pictureUrl;
    private int inStock;
    private int borrowed;
    @ManyToMany
    private List<User> listOfUsers;


    public void setIsbn(String newIsbn) {
        if (!newIsbn.contains("-")){
            throw new IllegalArgumentException("Every Isbn number needs multiple -.");
        }
        else {
            this.isbn = newIsbn;
        }
    }
}
