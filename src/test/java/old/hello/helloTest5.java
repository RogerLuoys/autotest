package old.hello;

import org.testng.annotations.Test;

public class helloTest5 {

    @Test
    public void Test(){
        String A = "{\"pointId\":\"516147574006788\",\"usablePoint\":2,\"expendPoint\":0,\"totalPoint\":1,\"status\":1}";
        String B = "{\"pointId\":\"516147574006788\",\"usablePoint\":2,\"expendPoint\":0,\"totalPoint\":1,\"status\":1}";
        String C = "usablePoint";
        Boolean r = checkFunction(A, B, C);
        System.out.println(r);
    }

    Boolean checkFunction(String valA, String valB, String valC) {
        if (valA == null || valB == null || valC == null) {
            System.out.print("有入参为空");
            return false;
        }
        String key = "\""+valC+"\"";
        if (!valA.contains(key) || !valB.contains(key)) {
            System.out.print("节点不存在");
            return false;
        }
        if (!valA.substring(0, 1).equals("{") || !valA.substring(valA.length()-1, valA.length()).equals("}")) {
            System.out.print("A格式不合法");
            return false;
        }
        if (!valB.substring(0, 1).equals("{") || !valB.substring(valA.length()-1, valA.length()).equals("}")) {
            System.out.print("B格式不合法");
            return false;
        }

        String valueA = valA.substring(valA.indexOf(key) + key.length());
        valueA = valueA.substring(1, valA.indexOf(","));
        String valueB = valB.substring(valB.indexOf(key) + key.length());
        valueB = valueB.substring(1, valB.indexOf(","));
        if (!valueA.equals(valueB)) {
            System.out.print("节点存在，但内容不相等");
            return false;
        }
        return true;
    }
}
