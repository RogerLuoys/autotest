package hello;

import org.testng.annotations.Test;

import java.util.Random;

public class helloTest2 {

    @Test
    public void Test1() {
        Random rd = new Random();
        for (int i = 0; i<100; i++) {
            long time1 = System.currentTimeMillis();
//            System.out.println(time1);
            int num = rd.nextInt(9);
//            System.out.println(num);
            String id = "1" + Long.toString(time1) + Long.toString(num);
            System.out.println(id);
        }


    }
}
