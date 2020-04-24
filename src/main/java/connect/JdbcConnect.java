package connect;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcConnect extends JdbcTemplate {

    public JdbcTemplate mysql1;

    public void setMysql1(JdbcTemplate mysql1) {
        this.mysql1 = mysql1;
    }

}
