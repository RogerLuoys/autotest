package api.impl;

import api.DB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DBJdbcTemplateImpl implements DB {

    private final String SELECT = "select";
    private final String UPDATE = "update";
    private final String COUNT = "count(1)";
    private final String DEFAULT_ORDER = " order by id desc";
    private final String DEFAULT_LIMIT = " limit 10";

    private DriverManagerDataSource dataSource = null;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public DBJdbcTemplateImpl() {}

    public DBJdbcTemplateImpl(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate.setDataSource(this.dataSource);
    }

    public DBJdbcTemplateImpl(String driver, String url, String userName, String password) {
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setDriverClassName(driver);
        this.dataSource.setUrl(url);
        this.dataSource.setUsername(userName);
        this.dataSource.setPassword(password);
        this.jdbcTemplate.setDataSource(dataSource);
    }

    @Deprecated
    @Override
    public void init(DriverManagerDataSource dataSource) {
        this.dataSource =dataSource;
        this.jdbcTemplate.setDataSource(this.dataSource);
    }

    @Override
    public Integer update(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(executeSql, UPDATE)) {
            System.out.println("-------->更新sql格式错误");
            return null;
        }
        int effectRow = count(transformUpdate2Select(executeSql));
        if (effectRow > 10) {
            System.out.println("-------->一次更新超过10行，请确认sql条件是否正确");
            return null;
        }
        return jdbcTemplate.update(executeSql);
    }

    @Override
    public Integer updateNoLimit(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(executeSql, UPDATE)) {
            System.out.println("-------->更新sql格式错误");
            return null;
        }
        int effectRow = count(transformUpdate2Select(executeSql));
        if (effectRow > 100) {
            System.out.println("-------->一次更新超过100行，请确认sql条件是否正确");
            return null;
        }
        return jdbcTemplate.update(executeSql);
    }

    @Override
    public Double sum(String sql) {
        return null;
    }

    @Override
    public Integer count(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(sql, COUNT)) {
            return null;
        }
        Map<String, Object> result = jdbcTemplate.queryForMap(executeSql);
        return Integer.valueOf(result.get(COUNT).toString());
    }

    @Override
    public List<Map<String, Object>> selectMultiRow(String sql) {
        if (!checkSqlType(sql, SELECT)) {
            return null;
        }
        String executeSql = addSelectDefault(sql);
        return jdbcTemplate.queryForList(executeSql);
    }

    @Override
    public Map<String, Object> select(String sql) {
        if (!checkSqlType(sql, SELECT)) {
            return null;
        }
        String executeSql = addSelectDefault(sql);
        return jdbcTemplate.queryForList(executeSql).get(0);
    }

    private String transformUpdate2Select(String updateSql) {
        int endIndex = updateSql.toLowerCase().indexOf(" set ");
        String tableName = updateSql.substring(7, endIndex);
        int startIndex = updateSql.toLowerCase().indexOf(" where ");
        String condition = updateSql.substring(startIndex);
        String selectSql = "select count(1) " + tableName + condition;
        return selectSql;
    }

    /**
     * 检查sql格式是否正确
     * @param sql
     * @param type
     * @return
     */
    private boolean checkSqlType(String sql, String type) {
        String[] sqlArray = sql.toLowerCase().split(" ");
        switch (type) {
            case SELECT:
                if (!sqlArray[0].equals(SELECT)) {
                    System.out.println("-------->查询sql类型不匹配");
                    return false;
                }
                return true;
            case UPDATE:
                if (!sqlArray[0].equals(UPDATE)) {
                    System.out.println("-------->更新sql类型不匹配");
                    return false;
                }
                return true;
            case COUNT:
                if (!sqlArray[0].equals(SELECT)) {
                    System.out.println("-------->查行数sql类型不匹配");
                    return false;
                }
                if (!sqlArray[1].equals(COUNT)) {
                    System.out.println("-------->查行数sql类型未包含count(1)");
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    /**
     * 默认按ID倒序，最多查10条
     * @param sql 完整的sql
     * @return 拼接默认查询规则后的sql
     */
    private String addSelectDefault(String sql) {
        String defaultSql = sql.replace(";", "");
        if (!defaultSql.toLowerCase().contains("order by")) {
            defaultSql = defaultSql + DEFAULT_ORDER;
        }
        if (!defaultSql.toLowerCase().contains("limit")) {
            defaultSql = defaultSql + DEFAULT_LIMIT;
        }
        return defaultSql + ";";
    }

}
