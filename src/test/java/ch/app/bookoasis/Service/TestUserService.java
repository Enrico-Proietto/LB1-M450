package ch.app.bookoasis.Service;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Repo.TestUserRepo;
import org.springframework.stereotype.Service;

@Service
public class TestUserService extends AbstractService<User> {
    private final TestUserRepo repo;
    public TestUserService(TestUserRepo repo) {
        super(repo);
        this.repo = repo;
    }
    public User findUserById(Long id) { return repo.findUserById(id); }
    public User findUserByEmail(String email) { return repo.findUserByEmail(email); }
    public User findUserByPhone(String phoneNumber) { return repo.findUserByPhone(phoneNumber); }

}
