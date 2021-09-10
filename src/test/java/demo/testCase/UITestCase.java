package demo.testCase;

import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;


public class UITestCase extends TestBase {

//    @BeforeClass
//    void beforeClass() {
//        auto.db.delete("sql");
//    }

    @Test
    void Test1(){
        auto.ui.init(auto.config.UI_URL);
        auto.ui.click("//span[text()='*****']");
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }

    @Test
    void Test2(){
        auto.ui.init(auto.config.UI_URL);
        auto.task.login();
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }

    @Test
    void test3() {
//        Date today = auto.timeUtil.setDateStart(2021, 9, 11);
//        int week = auto.timeUtil.getWeek(today);
//        System.out.println(today);
//        System.out.println(week);
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        System.out.println(c.getTime());


    }

//    @AfterClass
//    void afterClass() {
//        auto.db.delete("sql");
//    }

}
