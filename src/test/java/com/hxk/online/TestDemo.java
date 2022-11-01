package com.hxk.online;

import com.methodpackage.online_method.ReadToken;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.apache.xalan.xsltc.compiler.util.Util.println;

public class TestDemo {
    //未完成
    @BeforeMethod
    public void beforeTest() {
        System.out.println("@BeforeMethod - runOnceBeforeClass");
        ReadToken readToken = new ReadToken();
        String token = readToken.readTxt();

    }
    @Parameters({"myParam"})
    @Test(enabled = false,invocationCount = 10,threadPoolSize = 3)
    public void test1(String param){
        System.out.println("test1_1"+"param:"+param+"Thread id:"+Thread.currentThread().getId());
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
