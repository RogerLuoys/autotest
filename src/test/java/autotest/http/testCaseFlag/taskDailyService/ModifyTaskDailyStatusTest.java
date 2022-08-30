package autotest.http.testCaseFlag.taskDailyService;

import com.luoys.upgrade.flag.api.enums.TaskDailyStatusEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import autotest.http.testCaseFlag.FlagTestBase;

import java.util.HashMap;
import java.util.Map;

public class ModifyTaskDailyStatusTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "taskDaily/modifyTaskDailyStatus";

    @BeforeClass
    void resetData() {
        auto.flagDB.update("update task_daily set status=1 where task_daily_id='316182186796941'");
    }

    @Test
    void test1() {
        Reporter.log("准备入参");
        Map<String, Object> params = new HashMap<>();
        params.put("taskDailyId", "316182186796941");
        params.put("status", TaskDailyStatusEnum.UNDONE.getCode().toString());
        params.put("pointId", "516176799148478");

        Reporter.log("调用接口");
        auto.http.put(FullURL, null, null, params);

        Reporter.log("验证结果");
        String status = auto.flagDB.selectOneCell("select status from task_daily where task_daily_id='316182186796941'");
        Assert.assertEquals(status, TaskDailyStatusEnum.UNDONE.getCode().toString(), "校验状态是否变更");
    }
}
