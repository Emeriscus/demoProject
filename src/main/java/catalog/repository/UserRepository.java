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
        jdbcTemplate.update("insert into users(username, email, password, admin_rights) values (?,?,?,?)",
                user.getUsername(), user.getEmail(), user.getPassword(), user.isAdminRights());
    }

    public boolean isExistingUser(String username) {
        //language=sql
        jdbcTemplate.queryForObject("select * from users where users.username = ?",
                (rs, rowNum) -> rs.getString("username"), username);
        return true;
    }
}
