package com.hxk.lpt_app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.methodpackage.basic.testbasic;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Login_Smslogin extends testbasic {

    public String token;
    // 登录接口测试并获取token值
    public String lpt_login() throws Exception {

        // 通过HttpPost来发送post请求，带Body参数
        HttpPost httpPost = new HttpPost("https://route.liupinyike.com/sale/lptapp/login-smslogin");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("browser","iphone");
        jsonParam.put("phone","15990026146");
        jsonParam.put("platId",0);
        jsonParam.put("terminal","1");
        jsonParam.put("verifyCode","121212");
        jsonParam.put("youzanClentId","61e27e4bbd533f8c25");
        //添加headers内容
        httpPost.addHeader("system","eduOnline");

        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");

        // Headers的Content-Type类型
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        //打印httpPost
        System.out.println("请求是"+jsonParam);

        // 通过HttpClient来执行请求，获取一个响应结果
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 添加断言，获取服务器响应的状态码
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "服务器返回的状态码不是200");
        System.out.println("服务器响应的状态码为：" + statusCode);

        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        httpPost.setConfig(requestConfig);

        // 获取Response Headers的值
        Header[] headerArray = response.getAllHeaders(); //获取响应头信息,返回是一个数组
        HashMap<String, String> hm = new HashMap<String, String>(); //创建一个hashmap对象
        // 增强for循环遍历headerArray数组，依次把元素添加到hashmap集合
        for(Header header : headerArray) {
            hm.put(header.getName(), header.getValue());
        }
        // 打印hashmap
//        System.out.println("Response Headers的结果为："+ hm);

        // 获取Content-Type的类型
        HttpEntity httpentity = response.getEntity();

        // 获取Response Body结果
        String str = EntityUtils.toString(httpentity, "utf-8");
        System.out.println("登录接口的Response Body结果为：" + str);

        // 登录成功后，获取服务器返回Body值里的token值
        JSONObject json = JSON.parseObject(str); //将str的结果转换成json格式
        JSONObject data = json.getJSONObject("data");
        token = data.getJSONObject("tokenInfo").getString("refreshToken");
        System.out.println("login登录成功后的token值为：" + token);
        return token;
    }
    public static void  writeTxt(String str) throws Exception{

        File file=new File("./Api_AutoTestCase");
        if(!file.exists()){ //如果文件夹不存在
            file.mkdir(); //创建文件夹
        }
        FileWriter fw = null;
        String path = "./Api_AutoTestCase/lpt_token.txt";
        File f = new File(path);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(str.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
