package testCase.http.flag.flagBindService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class ModifyWitnessTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "flagBind/modifyWitness";
//
//    @BeforeClass
//    void resetData() {
//        auto.flagDB.update("update flag_bind set witness_id=null, witness_name=null where flag_id='116181201281961';");
//    }
//
//    @Test(description = "正常编辑见证人信息")
//    void test1() {
//        Reporter.log("准备入参");
//        FlagBindBO flagBindBO = new FlagBindBO();
//        flagBindBO.setFlagId("116181201281961");
//        flagBindBO.setWitnessId("233");
//        flagBindBO.setWitnessName("罗永胜");
//
//        Reporter.log("调用接口");
//        auto.http.put(FullURL, flagBindBO);
//
//        Reporter.log("验证结果");
//        String witnessId = auto.flagDB.selectOneCell("select witness_id from flag_bind where flag_id='116181201281961'");
//        String witnessName = auto.flagDB.selectOneCell("select witness_name from flag_bind where flag_id='116181201281961'");
//        Assert.assertEquals(witnessId, "233", "校验见证人Id编辑");
//        Assert.assertEquals(witnessName, "罗永胜", "校验见证人名字编辑");
//    }

    @Test(description = "正常编辑见证人信息")
    void test() {
        auto.sql.flag("update flag_bind set witness_id=null, witness_name=null where flag_id='116181201281961';");

        Reporter.log("调用接口");
        auto.http.put("flagBind/modifyWitness", "{\"flagId\":\"116181201281961\",\"witnessId\":\"233\",\"witnessName\":\"罗永胜\"}");

        Reporter.log("验证结果");
        String witnessId = auto.sql.flag("select witness_id from flag_bind where flag_id='116181201281961'");
        String witnessName = auto.sql.flag("select witness_name from flag_bind where flag_id='116181201281961'");
        auto.assertion.isEquals(witnessId, "233");
        auto.assertion.isEquals(witnessName, "罗永胜");

    }

}
