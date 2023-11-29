package ch.app.bookoasis.Tests.DatabaseTests;

import ch.app.bookoasis.Data.Role.Role;
import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Service.TestUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseUserTest {
    @Autowired
    private TestUserService service;
    private final String email = "maxmusterman@gmx.ch";
    @BeforeEach
    public void setUp() {
        User testUser = new User();
        testUser.setId(10000L);
        testUser.setFirstName("Hans");
        testUser.setLastName("Mustermann");
        testUser.setEmail(email);
        testUser.setPassword("Test");
        testUser.setAddress("Gründenstrasse 46");
        testUser.setCity("Binningen");
        testUser.setZip("4132");
        testUser.setCountry("Schweiz");
        testUser.setPhone("0615529595");
        testUser.setRole(Set.of(Role.USER));

        service.save(testUser);
    }

    @Test
    void findUser() {
        User userForTest = service.findUserByEmail(email);

        assertEquals("Hans", userForTest.getFirstName());
        assertEquals("Mustermann", userForTest.getLastName());
        assertEquals("Test", userForTest.getPassword());
        assertEquals("Gründenstrasse 46", userForTest.getAddress());

        service.delete(userForTest);
    }

    @Test
    void updateUser() {
        User userForTest = service.findUserByEmail(email);

        userForTest.setZip("4444");
        userForTest.setCountry("USA");
        userForTest.setCity("New York");
        userForTest.setAddress("Teststrasse 15");

        service.save(userForTest);

        User changedUser = service.findUserByEmail(email);

        assertEquals("4444",changedUser.getZip());
        assertEquals("I", changedUser.getCountry());
        assertNotEquals("Zürich", changedUser.getCity());
        assertNotEquals("Teststrasse 45", changedUser.getAddress());
        service.delete(changedUser);
    }

    @Test
    void deleteUser() {
        User userForTest = service.findUserByEmail(email);
        service.delete(userForTest);
        User changedUser = service.findUserByEmail(email);
        assertNull(changedUser);
    }
}
