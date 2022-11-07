package com.hxk.online.sale;

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
import org.testng.annotations.Test;

import java.util.HashMap;

public class Nchannel_list extends testbasic{

    @Test
    public void nchannel_list() throws Exception{
        ReadToken readToken = new ReadToken();
        String token = readToken.readTxt();

        String name = this.getClass().getName();
        Read_Folder read_folder = new Read_Folder();
        String url = read_folder.read_folder(name);
        Read_url read_url = new Read_url();
        String url_value = read_url.ReadFile(url);
        System.out.println("url_value:"+url_value);
    
        HttpGet httpGet=new HttpGet(url_value);
        httpGet.addHeader("Authorization",token);
        httpGet.addHeader("user-role-type","admin");
        httpGet.addHeader("system","eduOnline");
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
        System.out.println("nchannel_list接口的Response Body结果为：" + str);
        System.out.println(token);
        // 添加断言其二，获取服务器响应的状态码
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "服务器返回的状态码不是200");
        System.out.println("服务器响应的状态码为：" + statusCode);
    }
}