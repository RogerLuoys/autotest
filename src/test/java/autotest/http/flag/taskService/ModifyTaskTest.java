package autotest.http.flag.taskService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.http.flag.FlagTestBase;

public class ModifyTaskTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "task/modifyTask";

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.update("update task set description=null where task_id='216182271966145'");
//    }

    @Test
    void test1() {

        auto.sql.flag("update task set description=null where task_id='216182271966145'");

        Reporter.log("准备入参");
//        TaskBO taskBO = new TaskBO();
//        taskBO.setTaskId("216182271966145");
//        taskBO.setDescription("自动化脚本编辑的描述");

        Reporter.log("调用接口");
        auto.http.put("task/modifyTask", "{\"taskId\":\"216182271966145\",\"description\":\"自动化脚本编辑的描述\"}");

        Reporter.log("验证结果");
        String description = auto.sql.flag("select description from task where task_id='216182271966145'");
        Assert.assertEquals(description, "自动化脚本编辑的描述", "校验描述是否编辑成功");
    }
}
