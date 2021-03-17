package old.demo.testBase;

import base.FullAutoAPI;

public class TestBase {
    public FullAutoAPI auto = new FullAutoAPI();

    void test(){
        auto.http.doGet("");

    }
}
