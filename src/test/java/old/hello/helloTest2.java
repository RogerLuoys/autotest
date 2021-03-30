package old.hello;

import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class helloTest2 {

    @Test
    public void Test1() {
        Random rd = new Random();
        for (int i = 0; i<10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            long time1 = System.currentTimeMillis();
//            System.out.println(time1);
            int num = rd.nextInt(9);
//            System.out.println(num);
            String id = "1" + Long.toString(time1) + Long.toString(num);
            System.out.println(id);
        }
    }

    @Test
    public void Test2() throws URISyntaxException {

        String url = "http://118.24.117.181:9001/api/flag/queryFlagDetail";
        Map<String, Object> params = new HashMap<>();
        params.put("t1", true);
        params.put("t2", 1);
        params.put("t3", "char");
        URIBuilder uriBuilder = new URIBuilder(url);
        Iterator iterator = params.keySet().iterator();
        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            uriBuilder.setParameter(key, params.get(key).toString());
        }
        URI uri = uriBuilder.build();
        String result = uri.toString();
        System.out.println(result);
    }

//    @Test
//    public void Test3() {
//        UserBO obj = new UserBO();
//        obj.setUserid("user12345");
//        obj.setPassword("pw12345");
//        obj.setUserid("id1234");
//
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for(int i = 0; i < fields.length; i++) {
//            // 对于每个属性，获取属性名
//            String varName = fields[i].getName();
//            try {
//                // 获取原来的访问控制权限
//                boolean accessFlag = fields[i].isAccessible();
//                // 修改访问控制权限
//                fields[i].setAccessible(true);
//                // 获取在对象f中属性fields[i]对应的对象中的变量
//                Object o;
//                try {
//                    o = fields[i].get(obj);
//                    System.err.println("传入的对象中包含一个如下的变量：" + varName + " = " + o.toString());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                // 恢复访问控制权限
//                fields[i].setAccessible(accessFlag);
//            } catch (IllegalArgumentException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    private String transformObject2String (String url, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i = 0 , len = fields.length; i < len; i++) {
            // 对于每个属性，获取属性名
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o;
                try {
                    o = fields[i].get(obj);
                    System.err.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
