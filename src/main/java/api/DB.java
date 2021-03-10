package api;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface DB {

    void init(DriverManagerDataSource dataSource);

    /**
     * 默认按ID倒序，如果查询到多行数据，则只返回第一行
     * @param sql 完整的查询sql
     * @return key=列名，value=数据
     */
    Map<String, Object> select(String sql);

    /**
     * 默认按ID倒序，默认查询前10条数据
     * @param sql 完整的查询sql
     * @return
     */
    List<Map<String, Object>> selectMultiRow(String sql);

    /**
     * 查询行数
     * @param sql 完整的查询sql，请使用count(1)
     * @return 行数
     */
    Integer count(String sql);

    Double sum(String sql);

    /**
     * 更新影响行数不能超过10行
     * @param sql 完整的更新sql
     * @return
     */
    Integer update(String sql);

    Integer updateNoLimit(String sql);

}
