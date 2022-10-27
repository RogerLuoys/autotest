package testCase.http.flag.taskDailyService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class ModifyTaskDailyCommentTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "taskDaily/modifyTaskDailyComment";

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.update("update task_daily set comment=null where task_daily_id='316182238364858'");
//    }

    @Test
    void test1() {
        auto.sql.flag("update task_daily set comment=null where task_daily_id='316182238364858'");

        Reporter.log("准备入参");
//        Map<String, Object> params = new HashMap<>();
//        params.put("taskDailyId", "316182238364858");
//        params.put("comment", "自动化脚本编辑的描述");

        Reporter.log("调用接口");
        auto.http.put("taskDaily/modifyTaskDailyComment?taskDailyId=316182238364858&comment=自动化脚本编辑的描述");

        Reporter.log("验证结果");
        String comment = auto.sql.flag("select comment from task_daily where task_daily_id='316182238364858'");
        auto.assertion.isEquals(comment, "自动化脚本编辑的描述");
//        Assert.assertEquals(comment, "自动化脚本编辑的描述", "校验备注是否变更");
    }

}
