package autotest.http.flag.taskDailyService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.http.flag.FlagTestBase;


public class NewTaskDailyTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "taskDaily/newTaskDaily";
//
//    @BeforeClass
//    void resetData() {
//        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from task_daily where task_daily_name='自动化newTaskDaily测试')");
//        auto.flagDB.delete("delete from task_daily where task_daily_name='自动化newTaskDaily测试'");
//    }

    @Test(description = "新增临时任务")
    void test1() {
        auto.sql.flag("delete from flag_bind where flag_id in (select flag_id from task_daily where task_daily_name='自动化newTaskDaily测试')");
        auto.sql.flag("delete from task_daily where task_daily_name='自动化newTaskDaily测试'");

        Reporter.log("准备入参");
//        TaskDailyBO taskDailyBO = new TaskDailyBO();
//        taskDailyBO.setTaskDailyName("自动化newTaskDaily测试");
//        taskDailyBO.setPoint(10);
//        taskDailyBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        auto.http.post("taskDaily/newTaskDaily", "{\"taskDailyName\":\"自动化newTaskDaily测试\",\"point\":10,\"ownerId\":\"416176799148282\"}");

        Reporter.log("验证结果");
        String point = auto.sql.flag("select point from task_daily where task_daily_name='自动化newTaskDaily测试'");
        auto.assertion.isEquals(point, "10");
//        Assert.assertEquals(point, "10", "验证新增每日任务的信息");
    }

}
