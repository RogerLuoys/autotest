package testCase.http.flag.taskService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class QueryTaskByTaskIdTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "task/queryTaskByTaskId";

    @Test(description = "查询正常周期任务")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("task/queryTaskByTaskId?taskId=216179687468330");

        Reporter.log("验证结果");
        String taskName = auto.util.getJsonAny("taskName", result);
        auto.assertion.isEquals(taskName, "queryTaskByTaskId测试任务");
//        Assert.assertEquals(taskName, "queryTaskByTaskId测试任务", "校验查询到的任务名是否正确");
    }
}
