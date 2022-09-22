package com.hxk.online;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.apache.xalan.xsltc.compiler.util.Util.println;

public class TestDemo {
    @Parameters({"myParam"})
    @Test(enabled = false)
    public void test1(String param){
        System.out.println("test1_1"+param);
    }

    //parallel是否并行
    @DataProvider(name = "dataprovide",parallel = true)
    public Object[][] dataprovide(){
        return new Object[][]{
                {1,2},
                {3,4}
        };
    }

    @Test(dataProvider = "dataprovide",enabled = false)
    public void showDataprovide(int a,int b){
        System.out.println("DataProvider获得的参数para1如下："+a);
        System.out.println("DataProvider获得的参数para2如下："+b);;

    }

}
