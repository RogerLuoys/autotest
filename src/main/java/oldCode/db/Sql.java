package oldCode.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 试一下
 */
@Deprecated
public class Sql {

    private DriverManagerDataSource dataSource = null;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public String onePiece(String sql) {
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setDriverClassName("driver");
        this.dataSource.setUrl("url");
        this.dataSource.setUsername("userName");
        this.dataSource.setPassword("password");
        this.jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update(sql);
        jdbcTemplate.queryForList(sql);
        jdbcTemplate.execute(sql);
        return null;
    }

    public String runSql(String sql) {
        return null;
    }
}
