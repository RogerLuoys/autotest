package testCaseFlag.taskDailyService;

import com.luoys.upgrade.flag.api.bo.TaskDailyBO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class NewTaskDailyTest extends FlagTestBase {

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.delete("delete from from flag_bind where flag_id in (select flag_id from task_daily where task_daily_name='自动化newTaskDaily测试')");
//        auto.flagDB.delete("delete from from task_daily where task_daily_name='自动化newTaskDaily测试'");
//    }

    @Test
    void Test1() {
        TaskDailyBO taskDailyBO = new TaskDailyBO();
        taskDailyBO.setTaskDailyName("自动化newTaskDaily测试");
        taskDailyBO.setPoint(10);
        taskDailyBO.setOwnerId("416176799148282");
        auto.http.post(URL + "taskDaily/newTaskDaily", taskDailyBO);
        String point = auto.flagDB.selectOneCell("select point_id from task_daily where task_daily_name='自动化newTaskDaily测试'");
        Assert.assertEquals(point, "10", "验证新增每日任务的信息");
    }
}
