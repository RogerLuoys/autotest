package api.impl;

import api.DB;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

public class DBJdbcTemplateImpl implements DB {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();


    @Override
    public Boolean update(String sql) {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return null;
    }

    @Override
    public Double sum(String sql) {
        return null;
    }

    @Override
    public Integer count(String sql) {
        return null;
    }

    @Override
    public List<Map> selectMultiRow(String sql) {
        return null;
    }

    @Override
    public Map select(String sql) {
        return null;
    }

}
