package api;

public interface UTIL {

    /**
     * 把POJO对象转换成JSON字符串
     * @param object POJO对象
     * @return JSON格式的字符串
     */
    String toJSONString(Object object);

    /**
     * 根据JSON字符串中的节点名，取出对应的值；如果同名节点有多个，则只取第一个
     * @param json 完整的json字符串
     * @param name json字符串中的节点名
     * @return 名称对应的值
     */
    String getJSONData(String json, String name);
}
