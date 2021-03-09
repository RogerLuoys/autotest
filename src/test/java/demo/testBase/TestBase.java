package demo.testBase;

import base.Auto;
import base.FullAutoAPI;

public class TestBase {
    public FullAutoAPI auto = new FullAutoAPI();

    void test(){
        auto.http.doGet("");

    }
}
