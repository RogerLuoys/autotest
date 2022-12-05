package testCase.http.flag.taskService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class NewTaskTest extends FlagTestBase {

//    private final String FullURL = URL + "task/newTask";

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.delete("delete from task where flag_id='116182282686867'");
//    }

    @Test
    void test1() {
        auto.sql.flag("delete from task where flag_id='116182282686867'");

        Reporter.log("准备入参");
//        TaskBO taskBO = new TaskBO();
//        taskBO.setTaskName("自动化脚本新增任务");
//        taskBO.setCycle("1");
//        taskBO.setType(TaskTypeEnum.WEEK.getCode());
//        taskBO.setFlagId("116182282686867");
//        taskBO.setPoint(1024);

        Reporter.log("调用接口");
        auto.http.post("task/newTask", "{\"taskName\":\"216182271966145\",\"cycle\":\"1\",\"type\":\"2\",\"flagId\":\"116182282686867\",\"point\":1024}");

        Reporter.log("验证结果");
        String taskName = auto.sql.flag("select task_name from task where flag_id='116182282686867'");
        String type = auto.sql.flag("select type from task where flag_id='116182282686867'");
        String point = auto.sql.flag("select point from task where flag_id='116182282686867'");
        auto.assertion.isEquals(taskName, "自动化脚本新增任务");
        auto.assertion.isEquals(type, "2");
        auto.assertion.isEquals(point, "1024");
//        Assert.assertEquals(taskName, "自动化脚本新增任务", "校验新增任务名");
//        Assert.assertEquals(type, TaskTypeEnum.WEEK.getCode().toString(), "校验新增任务类型");
//        Assert.assertEquals(point, "1024", "校验新增任务积分");
    }
}
