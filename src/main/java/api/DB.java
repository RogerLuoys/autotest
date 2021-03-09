package api;

import java.util.List;
import java.util.Map;

public interface DB {

    Map select(String sql);

    Map select(String tableName, String codition);

    List<Map> selectMultiRow(String sql);

    Integer count(String sql);

    Double sum(String sql);

    Boolean update(String sql);

}
