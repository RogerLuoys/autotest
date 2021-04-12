package testCaseFlag.taskService;

import com.luoys.upgrade.flag.api.bo.TaskBO;
import com.luoys.upgrade.flag.api.enums.TaskTypeEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class NewTaskTest extends FlagTestBase {

    private final String FullURL = URL + "task/newTask";

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from task where flag_id='116182282686867'");
    }

    @Test
    void test1() {
        Reporter.log("准备入参");
        TaskBO taskBO = new TaskBO();
        taskBO.setTaskName("自动化脚本新增任务");
        taskBO.setCycle("1");
        taskBO.setType(TaskTypeEnum.WEEK.getCode());
        taskBO.setFlagId("116182282686867");
        taskBO.setPoint(1024);

        Reporter.log("调用接口");
        auto.http.post(FullURL, taskBO);

        Reporter.log("验证结果");
        String taskName = auto.flagDB.selectOneCell("select task_name from task where flag_id='116182282686867'");
        String type = auto.flagDB.selectOneCell("select type from task where flag_id='116182282686867'");
        String point = auto.flagDB.selectOneCell("select point from task where flag_id='116182282686867'");
        Assert.assertEquals(taskName, "自动化脚本新增任务", "校验新增任务名");
        Assert.assertEquals(type, TaskTypeEnum.WEEK.getCode().toString(), "校验新增任务类型");
        Assert.assertEquals(point, "1024", "校验新增任务积分");
    }
}
