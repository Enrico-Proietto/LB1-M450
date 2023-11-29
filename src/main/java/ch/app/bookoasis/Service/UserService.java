package ch.app.bookoasis.Service;

import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {
    private final UserRepo repo;
    public UserService(UserRepo repo) {
        super(repo);
        this.repo = repo;
    }
    public User findUserById(Long id) { return repo.findUserById(id); }
    public User findUserByEmail(String email) { return repo.findUserByEmail(email); }
    public User findUserByPhone(String phoneNumber) { return repo.findUserByPhone(phoneNumber); }

    private static List<GrantedAuthority> getAuthorities(User customer) {
        return customer.getRole().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User customer = repo.findUserByEmail(email);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), getAuthorities(customer));
    }
}
