package old.hello;

import org.testng.annotations.Test;

public class helloTest4 {

    @Test
    public void test1() {
        String test = convert("this is a test");
        System.out.println(test);
    }

    String convert(String val) {
        String[] vals = val.split(" ");
        StringBuilder convertVal = new StringBuilder("");
        for (int i=vals.length-1; i>=0; i--) {
            convertVal.append(vals[i]);
            convertVal.append(" ");
        }
        convertVal.delete(convertVal.length()-1, convertVal.length());
        return convertVal.toString();
    }
}
