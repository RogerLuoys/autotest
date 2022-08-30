package autotest;

import common.DbClient;

public class CommonSql {

    // 不占太多资源，可以直接实例化
    private final static CommonConfig config = new CommonConfig();
    private final static DbClient flag = new DbClient();
    private final static DbClient dzb = new DbClient();

    /**
     * 在flag数据库中执sql/n
     * 1、改删需要带上条件
     * 2、查询如果有多行，最多返回前10行
     * 3、查询如果只有单列，则去掉列名，只返回第一行的值
     *
     * @param sql 完整的sql，改删需要带条件，查询最多返回前10条
     * @return 执行结果
     */
    public String flag(String sql) {
        // 使用时才初始化
        flag.init(config.FLAG_DB_DRIVER,
                config.FLAG_DB_URL,
                config.FLAG_DB_USERNAME,
                config.FLAG_DB_PASSWORD);
        return flag.execute(sql);
    }

    /**
     * 在dzb数据库中执行sql（无数据源信息，仅供展示）
     * 1、改删需要带上条件
     * 2、查询如果有多行，最多返回前10行
     * 3、查询如果只有单列，则去掉列名，只返回第一行的值
     *
     * @param sql 完整的sql，改删需要带条件，查询最多返回前10条
     * @return 执行结果
     */
    public String dzb(String sql) {
        // 使用时才初始化
        dzb.init(config.DZB_DB_DRIVER,
                config.DZB_DB_URL,
                config.DZB_DB_USERNAME,
                config.DZB_DB_PASSWORD);
        return dzb.execute(sql);
    }
}
