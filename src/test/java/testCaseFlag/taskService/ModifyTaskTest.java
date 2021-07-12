package testCaseFlag.taskService;

import com.luoys.upgrade.flag.api.bo.TaskBO;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class ModifyTaskTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "task/modifyTask";

    @BeforeClass
    void resetData() {
        auto.flagDB.update("update task set description=null where task_id='216182271966145'");
    }

    @Test
    void test1() {
        Reporter.log("准备入参");
        TaskBO taskBO = new TaskBO();
        taskBO.setTaskId("216182271966145");
        taskBO.setDescription("自动化脚本编辑的描述");

        Reporter.log("调用接口");
        auto.http.put(FullURL, taskBO);

        Reporter.log("验证结果");
        String description = auto.flagDB.selectOneCell("select description from task where task_id='216182271966145'");
        Assert.assertEquals(description, "自动化脚本编辑的描述", "校验描述是否编辑成功");
    }
}
