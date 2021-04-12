package testCaseFlag.templateService;

import com.luoys.upgrade.flag.api.bo.FlagTemplateBO;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class UseFlagTemplateTest extends FlagTestBase {

    private final String FullURL = URL + "template/useFlagTemplate";

    @Test
    void test1() {
        FlagTemplateBO flagTemplateBO = new FlagTemplateBO();
        flagTemplateBO.setFlagName("自动化脚本转换模板");
        flagTemplateBO.setOwnerId("416176799148282");

        auto.http.post(FullURL, flagTemplateBO);

    }
}
