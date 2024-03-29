package testCase.http.flag.flagBindService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class QueryTaskDailyListTest extends FlagTestBase {

//    private final String FullURL = URL + "flagBind/queryTaskDailyList";
//
//    @Test(description = "正常获取一个月的任务列表数据")
//    void test1() {
//        Reporter.log("准备入参");
//        TaskDailyQueryBO taskDailyQueryBO = new TaskDailyQueryBO();
//        taskDailyQueryBO.setOwnerId("416176799148282");
//        taskDailyQueryBO.setStartTime(auto.timeUtil.setDateStart(2021, 4, 1));
//        taskDailyQueryBO.setEndTime(auto.timeUtil.setDateEnd(2021, 4, 30));
//
//        Reporter.log("调用接口");
//        String result = auto.http.post(FullURL, taskDailyQueryBO);
//
//        Reporter.log("验证数据");
//        String taskDailyName = auto.jsonUtil.getData(result, "taskDailyName");
//        Assert.assertEquals(taskDailyName, "queryReportDetail任务", "校验是否有指定任务");
//    }

    @Test(description = "正常获取一个月的任务列表数据")
    void test() {
        Reporter.log("调用接口");
        String result = auto.http.post("flagBind/queryTaskDailyList", "{\"startTime\":\"2021-04-01\",\"endTime\":\"2021-04-30\",\"ownerId\":\"416176799148282\"}");

        Reporter.log("验证数据");
        String taskDailyName = auto.util.getJsonAny("taskDailyName", result);
        auto.assertion.isEquals(taskDailyName, "queryReportDetail任务");
    }

}
