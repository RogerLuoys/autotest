package common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class DbClient {


    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private DriverManagerDataSource dataSource;

    DbClient() {
    }

    DbClient(String driver, String url, String username, String password) {
        dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbcTemplate.setDataSource(dataSource);
    }

    /**
     * 执行sql
     *
     * @param sql -
     * @return 全部执行成功则为true
     */
    public String execute(String sql) {
        String result;
        // 根据sql类型选择不同的执行方法
        // todo select 最多1000条，update必须有条件，delete必须有条件且最多10条
        if (sql.toLowerCase().contains("select")) {
            result = select(sql);
        } else if (sql.toLowerCase().contains("delete")) {
            result = delete(sql);
        } else if (sql.toLowerCase().contains("update")) {
            result = update(sql);
        } else if (sql.toLowerCase().contains("insert")) {
            result = insert(sql);
        } else {
            result = "不支持sql类型";
        }
        return result;
    }

    /**
     * 把更新sql转换为查询总行数的sql，查询sql以更新sql的条件为条件
     *
     * @param updateSql 更新sql，注意字段间空格只能有一个
     * @return select count(1) from 更新的表名 + 更新的条件
     */
    private String transformUpdate2Select(String updateSql) {
        int endIndex = updateSql.toLowerCase().indexOf(" set ");
        String tableName = updateSql.substring(7, endIndex);
        int startIndex = updateSql.toLowerCase().indexOf(" where ");
        String condition = updateSql.substring(startIndex);
        return "select count(1) from " + tableName + condition;
    }

    /**
     * 把删除sql转换为查询总行数的sql，查询sql以更新sql的条件为条件
     *
     * @param deleteSql 删除sql，注意字段间空格只能有一个
     * @return select count(1) from 删除的表名 + 删除的条件
     */
    private String transformDelete2Select(String deleteSql) {
        int startIndex = deleteSql.toLowerCase().indexOf(" from ");
        String condition = deleteSql.substring(startIndex);
        return "select count(1) " + condition;
    }

    /**
     * 执行更新sql语句
     *
     * @param sql 要执行的sql
     * @return 执行结果
     */
    private String update(String sql) {
        int effectRow = count(transformUpdate2Select(sql));
        if (effectRow > 200) {
            return "一次更新超过200行，请确认sql条件是否正确";
        } else if (effectRow == 0) {
            return "查无此类数据，不需要更新";
        }
        return String.valueOf(jdbcTemplate.update(sql));
    }

    /**
     * 执行插入sql语句
     *
     * @param sql 要执行的sql
     * @return 执行结果
     */
    private String insert(String sql) {
        return String.valueOf(jdbcTemplate.update(sql));
    }

    /**
     * 执行查询sql语句
     *
     * @param sql 要执行的sql
     * @return 执行结果
     */
    private String select(String sql) {
        return jdbcTemplate.queryForList(sql).toString();
    }

    /**
     * 执行删除sql语句
     *
     * @param sql 要执行的sql
     * @return 执行结果
     */
    private String delete(String sql) {
        int effectRow = count(transformDelete2Select(sql));
        if (effectRow > 50) {
            return "一次删除超过50行，请确认sql条件是否正确";
        } else if (effectRow == 0) {
            return "查无此类数据，不需要删除";
        }
        return String.valueOf(jdbcTemplate.update(sql));
    }

    /**
     * 查询总条数
     *
     * @param sql 查询语句
     * @return 总条数
     */
    private Integer count(String sql) {
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        String COUNT = "count(1)";
        return Integer.valueOf(result.get(COUNT).toString());
    }


    /**
     * 查询单格数据，sql中只能查询一个字段
     *
     * @param sql 完整查询sql (规则：select 单个列名 from 表名 where 条件)
     * @return -
     */
    public String selectOneCell(String sql) {
        String[] sqlList = sql.split(" ");
        //查询语句只能查询一列数据
        if (!sqlList[2].equalsIgnoreCase("from") || sqlList[1].equalsIgnoreCase("*") || sqlList[1].contains(",")) {
            log.warn("\n---->查询单格数据的sql格式不合规：{}", sql);
            return null;
        }
        Map<String, Object> result = jdbcTemplate.queryForList(sql).get(0);
        Object value = result.get(sqlList[1]);
        //时间格式转换
        if (value.getClass().getName().equals("java.time.LocalDateTime")) {
            LocalDateTime time = (LocalDateTime) value;
            DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
            return dateTimeFormatter.format(time);
        } else {
            return value.toString();
        }
    }

}
