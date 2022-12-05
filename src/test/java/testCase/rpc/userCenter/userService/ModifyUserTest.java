package testCase.rpc.userCenter.userService;

import org.testng.annotations.Test;
import testCase.rpc.userCenter.UserCenterTestBase;

public class ModifyUserTest extends UserCenterTestBase {

//    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
//    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);
//
//    @BeforeClass
//    void resetEnv() {
//        Reporter.log("还原数据");
//        auto.ucDB.update("update user set user_name='干旗人' where user_id='416170902167365';");
//    }
//
//    @Test(description = "正常修改名称")
//    void test1() {
//        Reporter.log("准备数据");
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId("416170902167365");
//        userDTO.setUserName("这是修改后的名字");
//
//        Reporter.log("调用modifyUser方法");
//        userService.modifyUser(userDTO);
//
//        Reporter.log("验证结果");
//        String userName = auto.ucDB.selectOneCell("select user_name from user where user_id='416170902167365';");
//        Assert.assertEquals(userName, "这是修改后的名字", "验证修改用户名字结果");
//    }
//
//
//    @Test(description = "不传userId")
//    void test2() {
//        Reporter.log("准备数据");
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("这是修改后的名字");
//
//        Reporter.log("调用modifyUser方法");
//        String result = auto.jsonUtil.toString(userService.modifyUser(userDTO));
//
//        Reporter.log("验证结果");
//        String message = auto.jsonUtil.getBaseData(result, "message");
//        Assert.assertEquals(message, "业务异常", "验证不传用户id修改");
//    }

    @Test(description = "正常修改名称")
    public void test() {

        // 数据还原
        auto.sql.uc("update user set user_name='干旗人' where user_id='416170902167365';");

        // 调rpc接口修改信息
        auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#modifyUser", "com.luoys.upgrade.uc.share.dto.UserDTO", "{\"userName\":\"这是修改后的名字\",\"userId\":\"416170902167365\"}");

        // 验证修改结果
        String userName = auto.sql.uc("select user_name from user where user_id='416170902167365';");
        auto.assertion.isEquals(userName, "这是修改后的名字");
    }


}
