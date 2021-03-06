package util.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JsonUTIL;

public class JsonUTILImpl implements JsonUTIL {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUTILImpl.class);

    /**
     * 判断json字符串的格式是否正常
     *
     * @param json json字符串
     * @return -
     */
    private Boolean checkFormat(String json) {
        if (!json.startsWith("{\"") || !json.endsWith("}")) {
            LOGGER.warn("JSON字符串格式不对");
            return false;
        } else if (countByString(json, "\"") % 2 != 0) {
            LOGGER.warn("JSON字符串格式不对");
            return false;
        } else if (countByString(json, "{") != countByString(json, "}")) {
            LOGGER.warn("JSON字符串格式不对");
            return false;
        } else if (countByString(json, "[") != countByString(json, "]")) {
            LOGGER.warn("JSON字符串格式不对");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 查询某关键字在字符串中出现的次数
     *
     * @param var 字符串
     * @param key 关键字
     * @return 关键字出现的次数
     */
    private int countByString(String var, String key) {
//        String subVar = var;
//        int counter = 0;
//        for (int i = 0; i < 1000; i++) {
//            if (subVar.contains(key)) {
//                subVar = subVar.substring(subVar.indexOf(key) + key.length());
//                counter++;
//            } else {
//                return counter;
//            }
//        }
//        return counter;

        return countByString(var, key, 0);
    }

    /**
     * 通过递归的方式，计算某关键字在字符串中出现的次数
     *
     * @param var   字符串
     * @param key   关键字
     * @param count 出现次数，初始填0
     * @return 出现的总次数
     */
    private int countByString(String var, String key, int count) {
        if (!var.contains(key)) {
            return count;
        } else {
            return countByString(var.substring(var.indexOf(key) + key.length()), key, count + 1);
        }
    }

    /**
     * 查询某节点的数据在JSON字符串中的起始位置
     *
     * @param json     完整json字符串
     * @param jsonName 某节点的名称
     * @return 名称对应值在json字符串中的起始位置，在‘：’后
     */
    private int getNodeStart(String json, String jsonName) {
//        int startIndex = 0;
//        // 去首尾{}
//        String leftJSON;
//        for (int i = 0; i < 100; i++) {
//            startIndex = json.indexOf(jsonName, startIndex) + jsonName.length();
//            leftJSON = json.substring(0, startIndex);
//            if (countByString(leftJSON, "{") - 1 != countByString(leftJSON, "}")) {
//                LOGGER.warn("非根节点，继续查找");
//                continue;
//            } else if (countByString(leftJSON, "[") != countByString(leftJSON, "]")) {
//                LOGGER.warn("非根节点，继续查找");
//                continue;
//            } else if (startIndex > json.length()) {
//                LOGGER.warn("无此根节点");
//                continue;
//            } else {
//                return startIndex;
//            }
//        }
//        return 0;
        return getNodeStart(json, jsonName, 0);
    }


    /**
     * 查询某节点的数据在JSON字符串中的起始位置
     * (递归版本)
     *
     * @param json     完整json字符串
     * @param jsonName 某节点的名称
     * @param startIndex 默认传入0
     * @return 名称对应值在json字符串中的起始位置，在‘：’后
     */
    private int getNodeStart(String json, String jsonName, int startIndex) {
        int nextStartIndex = json.indexOf(jsonName, startIndex) + jsonName.length();
        if (nextStartIndex == jsonName.length() - 1 || nextStartIndex >= json.length()) {
            LOGGER.warn("无此根节点");
            return -1;
        }
        String leftJSON = json.substring(0, nextStartIndex);
        if (countByString(leftJSON, "{") - 1 == countByString(leftJSON, "}")
                && countByString(leftJSON, "[") == countByString(leftJSON, "]")) {
//            LOGGER.info("节点数据起始位置查询成功");
            return nextStartIndex;
        } else {
            return getNodeStart(json, jsonName, nextStartIndex);
        }
    }

    /**
     * 查询某节点的数据在JSON字符串中的结束位置
     *
     * @param json 完整json字符串
     * @param startIndex 真实的节点数据起始位置，位置需在"："之后
     * @return 节点数据的终止位置
     */
    private int getNodeEnd(String json, int startIndex) {
        String jsonData = json.substring(startIndex);
        String typeLeft;
        String typeRight;
        if (jsonData.charAt(0) == '{') {
            typeLeft = "{";
            typeRight = "}";
        } else if (jsonData.charAt(0) == '[') {
            typeLeft = "[";
            typeRight = "]";
        } else if (!jsonData.contains(",")) {
            //刚好是最后一个节点，且该节点的值不是数组或对象
            return startIndex + jsonData.replace("}", "").replace("]", "").length();
        } else {
            //非最后一个节点，且节点值不是数组或对象
            return startIndex + jsonData.indexOf(",");
        }
        //节点的值是数组或对象，
        return getNodeEnd(json, typeLeft, typeRight, startIndex, 0);
    }

    /**
     * 查询某节点的数据在JSON字符串中的结束位置
     * (递归版本)
     *
     * @param json 完整json字符串
     * @param typeLeft 数据类型，{或[
     * @param typeRight 数据类型，}或]
     * @param startIndex 真实的节点数据起始位置，位置需在"："之后
     * @param endIndex 默认传0
     * @return 节点数据的终止位置
     */
    private int getNodeEnd(String json, String typeLeft, String typeRight, int startIndex, int endIndex) {
        //获取最近一个}或]的位置
        int nextEndIndex = json.indexOf(typeRight, endIndex) + 1;
        if (nextEndIndex >= json.length()) {
            LOGGER.warn("节点值不规范");
            return -1;
        }  else if (countByString(json.substring(startIndex, nextEndIndex), typeLeft)
                == countByString(json.substring(startIndex, nextEndIndex), typeRight)) {
            return nextEndIndex;
        } else {
            return getNodeEnd(json, typeLeft, typeRight, startIndex, nextEndIndex);
        }
    }

//    /**
//     * 取json字符串的第一个节点，需先把节点左侧字符去掉，不能单独使用
//     *
//     * @param jsonData 需要处理的字符串
//     * @return 字符串中的第一个节点
//     */
//    private String getFirstData(String jsonData) {
//        String subVar;
//        String typeLeft;
//        String typeRight;
//        if (jsonData.charAt(0) == '{') {
//            typeLeft = "{";
//            typeRight = "}";
//        } else if (jsonData.charAt(0) == '[') {
//            typeLeft = "[";
//            typeRight = "]";
//        } else if (jsonData.charAt(0) == '\"') {
//            return jsonData.substring(1, jsonData.indexOf(",") - 1);
//        } else if (!jsonData.contains(",")) {
//            return jsonData.replace("]", "").replace("}", "");
//        } else {
//            return jsonData.substring(0, jsonData.indexOf(","));
//        }
//        // 以{ 或 [ 开始s
//        int indexFrom = 0;
//        int endIndex;
//        for (int i = 0; i < 100; i++) {
//            endIndex = jsonData.indexOf(typeRight, indexFrom);
//            subVar = jsonData.substring(0, endIndex + 1);
//            //当左右符合相等时，获得完整最外层节点值
//            if (countByString(subVar, typeLeft) == countByString(subVar, typeRight)) {
//                return subVar;
//            }
//            indexFrom = endIndex + 1;
//        }
//        return null;
//    }

    @Override
    public String toString(Object object) {
        String json = JSON.toJSONString(object);
        LOGGER.info("\n====>转换后的json字符串为：{}", json);
        return json;
    }

    @Override
    public String getData(String json, String name) {
        return getData(json, name, 1);
    }

    @Override
    public String getData(String json, String name, int index) {
        String jsonName = "\"" + name + "\":";
        if (!checkFormat(json)) {
            LOGGER.warn("格式不对");
            return null;
        } else if (countByString(json, jsonName) < index) {
            LOGGER.warn("节点不存在，或超出数量");
            return null;
        }
        int startIndex = 0;
        for (int i = 0; i < index; i++) {
            startIndex = json.indexOf(jsonName, startIndex) + jsonName.length();
        }
//        String goHead = json.substring(startIndex);
//        String jsonResult = getFirstData(goHead);
        int endIndex = getNodeEnd(json, startIndex);
        String jsonResult = json.substring(startIndex, endIndex);
        //如果是字符串，去除首尾双引号
        if (jsonResult.startsWith("\"")) {
            jsonResult = jsonResult.substring(1, jsonResult.length() - 1);
        }
        LOGGER.info("\n====>获取到的json节点值为：{}", jsonResult);
        return jsonResult;
    }

    @Override
    public String getBaseData(String json, String name) {
        String jsonName = "\"" + name + "\":";
        if (!checkFormat(json)) {
            LOGGER.warn("格式不对");
            return null;
        } else if (countByString(json, jsonName) < 1) {
            LOGGER.warn("节点不存在");
            return null;
        }
        int startIndex = getNodeStart(json, jsonName);
//        String goHead = json.substring(startIndex);
//        String jsonResult = getFirstData(goHead);
        int endIndex = getNodeEnd(json, startIndex);
        String jsonResult = json.substring(startIndex, endIndex);
        //如果是字符串，去除首尾双引号
        if (jsonResult.startsWith("\"")) {
            jsonResult = jsonResult.substring(1, jsonResult.length() - 1);
        }
        LOGGER.info("\n====>获取到的json节点值为：{}", jsonResult);
        return jsonResult;
    }
}
