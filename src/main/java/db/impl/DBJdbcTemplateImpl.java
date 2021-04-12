package db.impl;

import db.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

public class DBJdbcTemplateImpl implements DB {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBJdbcTemplateImpl.class);
    private final static String SELECT = "select";
    private final static String UPDATE = "update";
    private final static String COUNT = "count(1)";
    private final static String DELETE = "delete";
    private final static String DEFAULT_ORDER = " order by id desc";
    private final static String DEFAULT_LIMIT = " limit 10";

    private DriverManagerDataSource dataSource = null;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public DBJdbcTemplateImpl() {
    }

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


    /**
     * 把更新sql转换为查询总行数的sql，查询sql以更新sql的条件为条件
     *
     * @param updateSql
     * @return
     */
    private String transformUpdate2Select(String updateSql) {
        int endIndex = updateSql.toLowerCase().indexOf(" set ");
        String tableName = updateSql.substring(7, endIndex);
        int startIndex = updateSql.toLowerCase().indexOf(" where ");
        String condition = updateSql.substring(startIndex);
        String selectSql = "select count(1) from " + tableName + condition;
        return selectSql;
    }

    /**
     * 把删除sql转换为查询总行数的sql，查询sql以更新sql的条件为条件
     *
     * @param deleteSql
     * @return
     */
    private String transformDelete2Select(String deleteSql) {
        int startIndex = deleteSql.toLowerCase().indexOf(" from ");
        String condition = deleteSql.substring(startIndex);
        String selectSql = "select count(1) " + condition;
        return selectSql;
    }

    /**
     * 检查sql格式是否正确
     *
     * @param sql
     * @param type
     * @return
     */
    private boolean checkSqlType(String sql, String type) {
        String[] sqlArray = sql.toLowerCase().split(" ");
        switch (type) {
            case SELECT:
                if (!sqlArray[0].equals(SELECT)) {
                    LOGGER.warn("\n---->查询sql类型不匹配");
                    return false;
                }
                return true;
            case UPDATE:
                if (!sqlArray[0].equals(UPDATE)) {
                    LOGGER.warn("\n---->更新sql类型不匹配");
                    return false;
                }
                return true;
            case COUNT:
                if (!sqlArray[0].equals(SELECT)) {
                    LOGGER.warn("\n---->查行数sql类型不匹配");
                    return false;
                }
                if (!sqlArray[1].equals(COUNT)) {
                    LOGGER.warn("\n---->查行数sql类型未包含count(1)");
                    return false;
                }
                return true;
            case DELETE:
                if (!sqlArray[0].equals(DELETE)) {
                    LOGGER.warn("\n---->更新sql类型不匹配");
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    /**
     * 加默认查询规则，默认按ID倒序，最多查10条，避免没必要的全表查询
     *
     * @param sql 完整的sql
     * @return 拼接默认查询规则后的sql
     */
    private String addSelectDefault(String sql) {
        String defaultSql = sql.replace(";", "");
        // 截取sql后缀，避免字符串中有同样的值
        int startIndex = Math.max(defaultSql.lastIndexOf("\""), defaultSql.lastIndexOf("'"));
        startIndex = Math.max(startIndex, defaultSql.lastIndexOf(")"));
        String suffixSql = defaultSql.substring(startIndex != -1 ? startIndex : 0).toLowerCase();

        if (!suffixSql.contains(" order by ")) {
            defaultSql = defaultSql + DEFAULT_ORDER;
        }
        if (!suffixSql.contains(" limit ")) {
            defaultSql = defaultSql + DEFAULT_LIMIT;
        }
        return defaultSql + ";";
    }

    @Deprecated
    @Override
    public void init(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate.setDataSource(this.dataSource);
    }

    @Override
    public Integer update(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(executeSql, UPDATE)) {
            LOGGER.warn("\n---->更新语句的sql格式错误：{}", sql);
            return null;
        }
        int effectRow = count(transformUpdate2Select(executeSql));
        if (effectRow > 10) {
            LOGGER.warn("\n---->一次更新超过10行，请确认sql条件是否正确");
            return null;
        } else if (effectRow == 0) {
            LOGGER.warn("\n---->查无此类数据，不需要更新");
            return null;
        }
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        return jdbcTemplate.update(executeSql);
    }

    @Override
    public Integer updateNoLimit(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(executeSql, UPDATE)) {
            LOGGER.warn("\n---->更新语句的sql格式错误：{}", sql);
            return null;
        }
        int effectRow = count(transformUpdate2Select(executeSql));
        if (effectRow > 100) {
            LOGGER.warn("\n---->一次更新超过100行，请确认sql条件是否正确");
            return null;
        } else if (effectRow == 0) {
            LOGGER.warn("\n---->查无此类数据，不需要更新");
            return null;
        }
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        return jdbcTemplate.update(executeSql);
    }

    @Override
    public Integer count(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(sql, COUNT)) {
            LOGGER.warn("\n---->查总数语句的sql格式错误：{}", sql);
            return null;
        }
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        Map<String, Object> result = jdbcTemplate.queryForMap(executeSql);
        return Integer.valueOf(result.get(COUNT).toString());
    }

    @Override
    public Map<String, Object> select(String sql) {
        if (!checkSqlType(sql, SELECT)) {
            LOGGER.warn("\n---->查询语句的sql格式错误：{}", sql);
            return null;
        }
        String executeSql = addSelectDefault(sql);
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(executeSql);
        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public List<Map<String, Object>> selectMultiRow(String sql) {
        if (!checkSqlType(sql, SELECT)) {
            LOGGER.warn("\n---->查询语句的sql格式错误：{}", sql);
            return null;
        }
        String executeSql = addSelectDefault(sql);
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        return jdbcTemplate.queryForList(executeSql);
    }

    @Override
    public String selectOneCell(String sql) {
        String[] sqlList = sql.split(" ");
        if (!sqlList[2].equalsIgnoreCase("from") || sqlList[1].equalsIgnoreCase("*")) {
            LOGGER.warn("\n---->查询单格数据的sql格式不正确：{}", sql);
            return null;
        }
        Map<String, Object> result = select(sql);
        return result.get(sqlList[1]).toString();
    }

    @Override
    public Integer delete(String sql) {
        String executeSql = sql.replace(";", "") + ";";
        if (!checkSqlType(executeSql, DELETE)) {
            LOGGER.warn("\n---->删除语句的sql格式错误：{}", sql);
            return null;
        }
        int effectRow = count(transformDelete2Select(executeSql));
        if (effectRow > 5) {
            LOGGER.warn("\n---->一次删除超过5行，请确认sql条件是否正确");
            return null;
        } else if (effectRow == 0) {
            LOGGER.warn("\n---->查无此类数据，不需要更新");
            return null;
        }
        LOGGER.info("\n====>最终执行sql：" + executeSql);
        return jdbcTemplate.update(executeSql);
    }
}
