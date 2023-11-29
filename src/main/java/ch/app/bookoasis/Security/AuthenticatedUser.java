package ch.app.bookoasis.Security;

import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Repo.UserRepo;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticatedUser {
    private final AuthenticationContext authenticationContext;
    private final UserRepo repo;
    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepo repo) {
        this.authenticationContext = authenticationContext;
        this.repo = repo;
    }
    @Transactional
    public Optional<User> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> repo.findUserByEmail(userDetails.getUsername()));
    }
    public void logout() { authenticationContext.logout(); }
}
