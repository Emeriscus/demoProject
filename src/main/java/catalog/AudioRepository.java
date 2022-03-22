package catalog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AudioRepository {

    private JdbcTemplate jdbcTemplate;

    public AudioRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveAudioAndGetId(long id, Audio audio) {
        //language=sql
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
                    PreparedStatement ps = conn.prepareStatement
                            ("insert into audios(library_items_id,title,performers,composers, year_of_publication) values (?,?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, id);
                    ps.setString(2, audio.getTitle());
                    ps.setString(3, StringListConverters.ListToString(audio.getPerformers()));
                    ps.setString(4, StringListConverters.ListToString(audio.getComposers()));
                    ps.setInt(5, audio.getYearOfPublication());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public Audio getAudioByLibraryItemsId(long id) {
        //language=sql
        return jdbcTemplate.queryForObject
                ("select * from library_items join audios on library_items.id=audios.library_items_id where library_items_id = ?",
                        (rs, rowNum) -> new Audio(rs.getString("title"),
                                StringListConverters.StringToList(rs.getString("performers")),
                                StringListConverters.StringToList("composers"),
                                rs.getInt("year_of_publication"),
                                rs.getInt("available_quantity")), id);
    }

    public void deleteAudioByLibraryItemsId(long libraryItemsId) {
        //language=sql
        jdbcTemplate.update("delete from audios where library_items_id = ?", libraryItemsId);
    }

}
