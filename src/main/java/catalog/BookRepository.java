package catalog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveBookAndGetId(long id, Book book) {
        //language=sql
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
                    PreparedStatement ps =
                            conn.prepareStatement("insert into books(library_items_id,title,authors,year_of_publication) values(?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, id);
                    ps.setString(2, book.getTitle());
                    ps.setString(3, StringListConverters.ListToString(book.getContributors()));
                    ps.setInt(4, book.getYearOfPublication());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public String getTitleById(long id) {
        //language=sql
        return jdbcTemplate.queryForObject("select title from books where id=?",
                (rs, rowNum) -> rs.getString("title"), id);
    }

    public Book getBookById(long id) {
        //language=sql
        return jdbcTemplate.queryForObject("select * from books where id = ?",
                (rs, rowNum) -> new Book
                        (rs.getString("title"), StringListConverters.StringToList(rs.getString("authors")),
                                rs.getInt("year_of_publication")), id);
    }

    public Book getBookByLibraryItemsId(long libraryItemsId) {
        //language=sql
        return jdbcTemplate.queryForObject
                ("select * from library_items join books ON library_items.id = books.library_items_id WHERE library_items_id=?",
                        (rs, rowNum) -> new Book
                                (rs.getString("title"),
                                        StringListConverters.StringToList(rs.getString("authors")),
                                        rs.getInt("year_of_publication"),
                                        rs.getInt("available_quantity")), libraryItemsId);
    }

    public void deleteBookByLibraryItemsId(long libraryItemsId) {
        //language=sql
        jdbcTemplate.update("delete from books where library_items_id = ?", libraryItemsId);
    }
}
