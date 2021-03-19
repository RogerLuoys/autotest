package api;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface DB {

    @Deprecated
    void init(DriverManagerDataSource dataSource);

    /**
     * 默认按ID倒序，如果查询到多行数据，则只返回第一行
     * @param sql 完整的查询sql
     * @return key=列名，value=数据
     */
    Map<String, Object> select(String sql);

    /**
     * 可查询多行，默认上限10行，可通过limit自定义行数
     * @param sql 完整的查询sql
     * @return
     */
    List<Map<String, Object>> selectMultiRow(String sql);

    /**
     * 查询行数
     * @param sql 完整的查询sql，需使用count(1)
     * @return 行数
     */
    Integer count(String sql);

    /**
     * 更新影响行数不能超过10行
     * @param sql 完整的更新sql
     * @return
     */
    Integer update(String sql);

    /**
     * 更新影响行数不能超过100行
     * @param sql 完整的更新sql
     * @return
     */
    Integer updateNoLimit(String sql);

    Integer delete(String sql);

}
