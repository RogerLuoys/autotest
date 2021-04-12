package db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface DB {

    /**
     * 会更改数据库连接信息，慎用
     *
     * @param dataSource 数据库信息
     */
    @Deprecated
    void init(DriverManagerDataSource dataSource);

    /**
     * 默认按ID倒序，如果查询到多行数据，则只返回第一行
     *
     * @param sql 完整的查询sql (规则：select 需要的列 from 表名 where 条件)
     * @return key=列名，value=数据
     */
    Map<String, Object> select(String sql);

    /**
     * 可查询多行，默认上限10行，可通过limit自定义行数
     *
     * @param sql 完整的查询sql (规则：select 需要的列 from 表名 where 条件)
     * @return Map中的Key对应列名、Value对应该列的某一个数据
     */
    List<Map<String, Object>> selectMultiRow(String sql);

    /**
     * 查询单格数据，sql中只能查询一个字段
     *
     * @param sql 完整查询sql (规则：select 单个列名 from 表名 where 条件)
     * @return -
     */
    String selectOneCell(String sql);

    /**
     * 查询行数，需使用count(1)
     *
     * @param sql 完整的查询sql (规则：select count(1) from 表名 where 条件)
     * @return 行数
     */
    Integer count(String sql);

    /**
     * 更新数据，影响行数不能超过10行
     *
     * @param sql 完整的更新sql (规则：update 表名 set 更新字段 where 条件)
     * @return
     */
    Integer update(String sql);

    /**
     * 更新数据，影响行数不能超过100行
     *
     * @param sql 完整的更新sql (规则：update 表名 set 更新字段 where 条件)
     * @return
     */
    Integer updateNoLimit(String sql);

    /**
     * 删除数据，影响行数不能超过5条
     *
     * @param sql 完整的删除sql (规则：delete from 表名 where 条件)
     * @return
     */
    Integer delete(String sql);

}
