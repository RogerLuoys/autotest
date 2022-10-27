package commonClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class UtilClient {


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
        }  else if (countByString(json.substring(startIndex, nextEndIndex), typeLeft, 0)
                == countByString(json.substring(startIndex, nextEndIndex), typeRight, 0)) {
            return nextEndIndex;
        } else {
            return getNodeEnd(json, typeLeft, typeRight, startIndex, nextEndIndex);
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
     * 通过key值，获取对应得json值
     *
     * @param key json字符串的key
     * @param json json字符串本身
     * @return key对应的value
     */
    public String getJsonValue(String key, String json) {
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
    public String getFirstValue(String key, String json) {
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
     * 设置时间
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @param hour 时（0-23）
     * @param minute 分（0-59）
     * @param second 秒（0-59）
     * @return -
     */
    public Date setDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        //calendar月份从0开始算！
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置时间，时分秒都默认为1
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDate(int year, int month, int day) {
        return setDate(year, month, day, 1, 1, 1);
    }

    /**
     * 设置时间，时分秒都默认为0\0\30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDateStart(int year, int month, int day) {
        return setDate(year, month, day, 0, 0, 30);
    }

    /**
     * 设置时间，时分秒都默认为23/59/30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDateEnd(int year, int month, int day) {
        return setDate(year, month, day, 23, 59, 30);
    }

    /**
     * 根据日期取得星期几
     * @param date
     * @return
     */
    public int getWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }


    /**
     * 在指定目录下创建一个文件，如果目录不存在则会先创建目录
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    public boolean createFile(String filePath) {
        int nameIndex = filePath.lastIndexOf("\\") + 1;
        String directory = filePath.substring(0, nameIndex);
        String fileName = filePath.substring(nameIndex);
        File dir = new File(directory);
        if (dir.exists() == false) {
            dir.mkdirs(); // the directory is created
        }
        File file = new File(directory + "//" + fileName);

        try {
            if (file.exists()) {
                System.out.println("File exists!");
                return false;
            } else {
                file.createNewFile();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除指定文件
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }
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
