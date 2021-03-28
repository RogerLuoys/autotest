package api.impl;

import api.UTIL;
import com.alibaba.fastjson.JSON;

public class UTILImpl implements UTIL {

    private final String JSON_OBJECT="{}";
    private final String JSON_ARRAY="[]";

    private Boolean checkJSONFormat(String json) {
        return true;
    }

    private int countByString(String var, String key) {
        String subVar = var;
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            if (subVar.contains(key)) {
                subVar = subVar.substring(subVar.indexOf(key) + key.length());
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    private String getFirstData(String jsonData) {
        String subVar;
        String typeLeft;
        String typeRight;
        if (jsonData.charAt(0) == '{') {
            typeLeft = "{";
            typeRight = "}";
        } else if(jsonData.charAt(0) == '[') {
            typeLeft = "[";
            typeRight = "]";
        } else {
            return jsonData.substring(0, jsonData.indexOf(","));
        }
        // 以{ 或 [ 开始s
        int indexFrom = 0;
        int endIndex;
        for (int i = 0; i < 100; i++) {
            endIndex = jsonData.indexOf(typeRight, indexFrom);
            subVar = jsonData.substring(0,  endIndex + 1);
            //当左右符合相等时，获得完整最外层节点值
            if (countByString(subVar, typeLeft) == countByString(subVar, typeRight)) {
                return subVar;
            }
            indexFrom = endIndex + 1;
        }
        return null;
    }

    @Override
    public String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public String getJSONData(String json, String name) {
        String jsonName = "\"" + name + "\":";
        if (!checkJSONFormat(json)) {
            System.out.println("格式不对");
            return null;
        }
        int startIndex = json.indexOf(jsonName) + jsonName.length();
        String goHead = json.substring(startIndex);
        return getFirstData(goHead);
    }
}
