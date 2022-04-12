package catalog.service;

import catalog.classes.Admin;
import catalog.classes.User;
import catalog.repository.UserRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private Flyway flyway;
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void init() {

        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/library-catalog-test?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        flyway = Flyway.configure()/*.locations("src/test/resources/db/migration")*/.dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        userRepository = new UserRepository(dataSource);
        userService = new UserService(userRepository);

        userService.addUser(new Admin("emeriscus@gmail.com", "wP.X-L[[c&D<×I/2-Đ?VCnTZ(P,{kg",
                "LrSGYAfmDGBQsAZkwD3BXz1TEalUeU2155ODJt3YJ4A="));
    }

    @Test
    void addUserTest() {
        userService.addUser(new User("rsgf@sdg.com", "sdgsfgvsdgsdgsdgrghzmhwe4t242", "32534t4wt5fdg8v94zt9tw4"));

        assertEquals("sdgsfgvsdgsdgsdgrghzmhwe4t242", userService.getStoredSaltByEmail("rsgf@sdg.com"));
        assertEquals("32534t4wt5fdg8v94zt9tw4", userService.getStoredPasswordByEmail("rsgf@sdg.com"));
        assertFalse(userService.hasAdminRightByEmail("rsgf@sdg.com"));
    }

    @Test
    void isExistingUserWithExistUserTest() {
        assertTrue(userService.isExistingUser("emeriscus@gmail.com"));
    }

    @Test
    void isExistingUserWithNotExistUserTest() {
        assertFalse(userService.isExistingUser("dsfdadfef@aef.hu"));
    }
}