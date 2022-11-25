package testCase.ui.reader.main;

import org.testng.annotations.Test;
import testCase.ui.reader.ReaderTestBase;

public class InitTest extends ReaderTestBase {

    @Test
    public void test() {
        auto.ui.click("//*[@text='同意并进入']");
        auto.ui.click("//*[@text='立即进入']");
        auto.ui.click("//*[@text='好的']");
        auto.assertion.isXpathExist("//*[@text='确定']");
    }

}
