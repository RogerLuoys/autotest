package testCaseFlag.taskDailyService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

import java.util.HashMap;
import java.util.Map;

public class ModifyTaskDailyCommentTest extends FlagTestBase {

    private final String FullURL = URL + "taskDaily/modifyTaskDailyComment";

    @BeforeClass
    void resetData() {
        auto.flagDB.update("update task_daily set comment=null where task_daily_id='316182238364858'");
    }

    @Test
    void test1() {
        Reporter.log("准备入参");
        Map<String, Object> params = new HashMap<>();
        params.put("taskDailyId", "316182238364858");
        params.put("comment", "自动化脚本编辑的描述");

        Reporter.log("调用接口");
        auto.http.put(FullURL, null, null, params);

        Reporter.log("验证结果");
        String comment = auto.flagDB.selectOneCell("select comment from task_daily where task_daily_id='316182238364858'");
        Assert.assertEquals(comment, "自动化脚本编辑的描述", "校验备注是否变更");
    }

}
