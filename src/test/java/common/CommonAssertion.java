package common;

/**
 * 自定义断言
 * 也可以继承Assert类
 */
public class CommonAssertion extends AssertionClient {

    CommonUi ui;

    public CommonAssertion() {
        super();
    }

    public CommonAssertion (CommonUi ui) {
        super(ui);
        this.ui = ui;
    }

}
