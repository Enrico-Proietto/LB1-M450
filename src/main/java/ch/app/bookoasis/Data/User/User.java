package ch.app.bookoasis.Data.User;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.Role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String zip;
    @NotNull
    private String country;
    @Column(unique = true)
    @NotNull
    private String phone;
    @NotNull
    private Set<Role> role;
    @ManyToMany
    private List<Book> booksBorrowed;

    public void setEmail(String newEmail) {
        if (!newEmail.contains("@")){
            throw new IllegalArgumentException("for a new Email an @ is required.");
        }
        else {
            this.email = newEmail;
        }
    }
}
