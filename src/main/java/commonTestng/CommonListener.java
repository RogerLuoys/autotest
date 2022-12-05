package commonTestng;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * 自定义监听器
 */
public class CommonListener extends TestListenerAdapter implements IAnnotationTransformer {

    // testng重试失败的用例
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retryAnalyzer = iTestAnnotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }

    // testng 重新计算结果，去除因重试而产生的多余ignored
    @Override
    public void onFinish(ITestContext iTestContext) {

        super.onFinish(iTestContext);

        // 获取所有跳过的结果
        Iterator skipTestResults = iTestContext.getSkippedTests().getAllResults().iterator();
        while (skipTestResults.hasNext()) {
            ITestResult skip = (ITestResult) skipTestResults.next();
            ITestNGMethod method = skip.getMethod();
            // 判断所有跳过的case中是否有成功或者失败的结果，如果有就删除该结果
            if (iTestContext.getFailedTests().getResults(method).size() > 0 || iTestContext.getPassedTests().getResults(method).size() > 0) {
                skipTestResults.remove();
            }
        }
    }
}
