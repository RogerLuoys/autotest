package commonTestng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * testng重试失败的用例
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    // 重试次数
    private int count = 0;
    // 最大重试次数
    final private static int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < MAX_RETRY_COUNT) {
            count++;
            return true;
        }
        // 对于使用了@DataProvider数据驱动的情况，必须重置重试次数，否则只有第一个case会重试
        count = 0;
        return false;
    }
}
