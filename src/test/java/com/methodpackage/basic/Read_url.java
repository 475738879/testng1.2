package com.methodpackage.basic;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;


public class Read_url {
    /**
     * 通过本地文件访问json并读取
     */
    public static String ReadFile(String path){
//        System.out.println("path:"+path);


        //读qu前半段
        Map<String,String> map = new HashMap<>();
        ResourceBundle properties = ResourceBundle.getBundle("config");
        Enumeration<String> keys = properties.getKeys();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = properties.getString(key);
            map.put(key,value);
        }
//        System.out.println("map:"+map);
        String hj = map.get("env");
        String value2 = map.get(hj);
        //读qu后半段
        String[] url = path.split("\\.");
        for(int i=0;i < url.length;i++){
            System.out.println("url:"+url[i]);
        }

        String laststr="";
        String value ="";
        BufferedReader reader=null;
        try {
            //读取json文件并转化为list<Map>格式
            File file = new File("src/test/resources/api-"+url[0]+".json");
            String jsonString = FileUtils.readFileToString(file, "UTF-8");
            //jsonString结果不是json数组格式的字符串这里需要拼接成json数组格式的字符串
            String  jsonArrayString = String.format("%s%s%s", "[",jsonString,"]");
//            System.out.println("jsonArrayString:"+jsonArrayString);
            List<Map> list =  JSON.parseArray(jsonArrayString, Map.class);
//            System.out.println("list:"+list);

            for (Map map2 : list) {
                String type =String.valueOf(map2.get("API_"+url[1]));
//                System.out.println("type:"+type);
                HashMap hashMap = JSON.parseObject(type, HashMap.class);
//                System.out.println("hashMap:"+hashMap);
                value = hashMap.get("apiPath").toString();
//                System.out.println("value:"+value);
            }

//            System.out.println("list:"+list);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException el){
                }
            }
        }

        laststr = value2+value+"?";
//        System.out.println("laststr:" + laststr);
        return laststr;
    }

}
