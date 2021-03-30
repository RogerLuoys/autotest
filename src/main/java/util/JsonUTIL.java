package util;

public interface JsonUTIL {

    /**
     * 把POJO对象转换成JSON字符串
     * @param object POJO对象
     * @return JSON格式的字符串
     */
    String toString(Object object);

    /**
     * 根据JSON字符串中的节点名，取出对应的值；如果同名节点有多个，则只取第一个
     * 如JSON中包含数组或对象，也纳入取值
     * @param json 完整的json字符串
     * @param name json字符串中的节点名
     * @return 名称对应的值
     */
    String getData(String json, String name);

    /**
     * 根据JSON字符串中的节点名，取出对应的值；如果同名节点有多个，则只取第 index 个
     * 如JSON中包含数组或对象，也纳入取值
     * @param json 完整的json字符串
     * @param name json字符串中的节点名
     * @param index 从左往右同名节点的序号，从1开始
     * @return 名称对应的值
     */
    String getData(String json, String name, int index);

    /**
     * 根据JSON字符串中的节点名，取出对应的值
     * @param json 完整的json字符串
     * @param name json字符串中的节点名
     * @return 名称对应的值
     */
    String getBaseData(String json, String name);
}
