package demo.testBase;

import base.Auto;
import base.SupperTestBase;

public class TestBase {
    public Auto auto = new Auto();

    void test(){
        auto.http.doGet("");

    }
}
