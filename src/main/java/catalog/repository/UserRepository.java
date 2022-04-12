package catalog.repository;

import catalog.classes.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addUser(User user) {
        //language=sql
        jdbcTemplate.update("insert into users(email, salt, secure_password, admin_rights) values (?,?,?,?)",
                user.getEmail(), user.getSalt(), user.getSecurePassword(), user.isAdminRights());
    }

    public String getStoredPasswordByEmail(String email) {
        //language=sql
        return jdbcTemplate.queryForObject("select * from users where email=?",
                (rs, rowNum) -> rs.getString("secure_password"), email);
    }

    public String getStoredSaltByEmail(String email) {
        //language=sql
        return jdbcTemplate.queryForObject("select * from users where email=?",
                (rs, rowNum) -> rs.getString("salt"), email);
    }

    public boolean HasAdminRightByEmail(String email) {
        //language=sql
        return jdbcTemplate.queryForObject("select * from users where email=?",
                (rs, rowNum) -> rs.getBoolean("admin_rights"), email);
    }

    public void queryByEmail(String email) {
        //language=sql
        jdbcTemplate.queryForObject("select * from users where users.email = ?",
                (rs, rowNum) -> rs.getString("email"), email);
    }
}
