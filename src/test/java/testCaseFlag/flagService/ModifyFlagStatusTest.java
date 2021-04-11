package testCaseFlag.flagService;

import com.luoys.upgrade.flag.api.enums.FlagStatusEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class ModifyFlagStatusTest extends FlagTestBase {

    private final String FullURL = URL + "flag/modifyFlagStatus";

    @BeforeClass
    void resetData() {
        auto.flagDB.update("update flag set status=1 where flag_id='116181353720252'");
    }

    @Test(description = "正常更改flag状态")
    void test1() {
        Reporter.log("调用接口");
        auto.http.put(FullURL + "?flagId=116181353720252&status=" + FlagStatusEnum.IN_PROGRESS.getCode());

        Reporter.log("验证结果");
        String status = auto.flagDB.selectOneCell("select status from flag where flag_id='116181353720252'");
        Assert.assertEquals(status, FlagStatusEnum.IN_PROGRESS.getCode().toString(), "校验更改后的状态");
    }
}
