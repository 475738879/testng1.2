package com.hxk.online;

import com.methodpackage.basic.Read_Folder;
import com.methodpackage.basic.Read_url;
import com.methodpackage.online_method.ReadToken;
import com.methodpackage.basic.testbasic;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class Index extends testbasic {
    @Test
    public void index() throws Exception{
//        Login login = new Login();
//        String token = login.login();
        ReadToken readToken = new ReadToken();
        String token = readToken.readTxt();

        //得到路径，以获取当前类的目录名和类名的方式
        String name = this.getClass().getName();
        Read_Folder read_folder = new Read_Folder();
        String url = read_folder.read_folder(name);
        Read_url read_url = new Read_url();
        String url_value = read_url.ReadFile(url);
        System.out.println("url_value:"+url_value);

//        Properties properties = new Properties();
//        properties.load(this.getClass().getClassLoader().getResourceAsStream("config"));
        HttpGet httpGet=new HttpGet(url_value);
        httpGet.addHeader("page","1");
        httpGet.addHeader("per_page","20");
        httpGet.addHeader("Authorization",token);
        httpGet.addHeader("user-role-type","admin");
//        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
//        entity.setContentEncoding("UTF-8");
//
//        // Headers的Content-Type类型
//        entity.setContentType("application/json");
//        httpGet.setEntity(entity);

        // 通过HttpClient来执行请求，获取一个响应结果
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 添加断言其一，获取服务器响应的状态码
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();

        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        httpGet.setConfig(requestConfig);

        // 获取Response Headers的值
        Header[] headerArray = response.getAllHeaders(); //获取响应头信息,返回是一个数组
        HashMap<String, String> hm = new HashMap<String, String>(); //创建一个hashmap对象
        // 增强for循环遍历headerArray数组，依次把元素添加到hashmap集合
        for(Header header : headerArray) {
            hm.put(header.getName(), header.getValue());
        }

        // 获取Content-Type的类型
        HttpEntity httpentity = response.getEntity();
        // 获取Response Body结果
        String str = EntityUtils.toString(httpentity, "utf-8");
//        System.out.println("index接口的Response Body结果为：" + str);
        // 添加断言其二，获取服务器响应的状态码
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "服务器返回的状态码不是200");
        System.out.println("服务器响应的状态码为：" + statusCode);
    }
}
