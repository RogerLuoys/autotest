package commonClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class UtilCommonClient {


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
            return this.countByString(var.substring(var.indexOf(key) + key.length()), key, count + 1);
        }
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
            log.warn("节点值不规范");
            return -1;
        }  else if (this.countByString(json.substring(startIndex, nextEndIndex), typeLeft, 0)
                == this.countByString(json.substring(startIndex, nextEndIndex), typeRight, 0)) {
            return nextEndIndex;
        } else {
            return this.getNodeEnd(json, typeLeft, typeRight, startIndex, nextEndIndex);
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
        return this.getNodeEnd(json, typeLeft, typeRight, startIndex, 0);
    }

    /**
     * 通过key值，获取对应得json值
     *
     * @param key json字符串的key
     * @param json json字符串本身
     * @return key对应的value
     */
    public String getJson(String key, String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString(key);
    }

    /**
     * 根据JSON字符串中的节点名，取出对应的值；如果同名节点有多个，则只取第 index 个
     * 如JSON中包含数组或对象，也纳入取值
     *
     * @param key  json字符串中的节点名
     * @param json  完整的json字符串
     * @return 名称对应的值
     */
    public String getJsonAny(String key, String json) {
        String jsonName = "\"" + key + "\":";
        int startIndex = json.indexOf(jsonName) + jsonName.length();
        int endIndex = getNodeEnd(json, startIndex);
        String jsonResult = json.substring(startIndex, endIndex);
        //如果是字符串，去除首尾双引号
        if (jsonResult.startsWith("\"")) {
            jsonResult = jsonResult.substring(1, jsonResult.length() - 1);
        }
        log.info("\n====>获取到的json节点值为：{}", jsonResult);
        return jsonResult;
    }


    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    public void sleep(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            log.error("\n---->线程睡眠异常");
        }
    }

}
