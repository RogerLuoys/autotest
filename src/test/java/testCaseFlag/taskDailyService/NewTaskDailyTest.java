package testCaseFlag.taskDailyService;

import com.luoys.upgrade.flag.api.bo.TaskDailyBO;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;


public class NewTaskDailyTest extends FlagTestBase {

    private final String FullURL = URL + "taskDaily/newTaskDaily";

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from task_daily where task_daily_name='???newTaskDaily??')");
        auto.flagDB.delete("delete from task_daily where task_daily_name='???newTaskDaily??'");
    }

    @Test(description = "新增临时任务")
    void test1() {
        Reporter.log("准备入参");
        TaskDailyBO taskDailyBO = new TaskDailyBO();
        taskDailyBO.setTaskDailyName("自动化newTaskDaily测试");
        taskDailyBO.setPoint(10);
        taskDailyBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        auto.http.post(FullURL, taskDailyBO);

        Reporter.log("验证结果");
        String point = auto.flagDB.selectOneCell("select point from task_daily where task_daily_name='自动化newTaskDaily测试'");
        Assert.assertEquals(point, "10", "验证新增每日任务的信息");
    }

}
