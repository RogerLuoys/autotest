package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.dto.UserDTO;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

public class ModifyUserTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @BeforeClass
    void resetEnv() {
        auto.ucDB.update("update user set user_name='干旗人' where user_id='416170902167365';");
    }

    @Test(description = "正常修改名称")
    void test1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("416170902167365");
        userDTO.setUserName("这是修改后的名字");
        userService.modifyUser(userDTO);
        String userName = auto.ucDB.selectOneCell("select user_name from user where user_id='416170902167365';");
        Assert.assertEquals(userName, "这是修改后的名字", "验证修改用户名字结果");
    }


    @Test(description = "不传userId")
    void test2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("这是修改后的名字");
        String result = auto.jsonUtil.toString(userService.modifyUser(userDTO));
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "业务异常", "验证不传用户id修改");
    }

}
