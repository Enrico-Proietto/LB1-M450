package ch.app.bookoasis.Repo;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepo extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByPhone(String phoneNumber);
}
