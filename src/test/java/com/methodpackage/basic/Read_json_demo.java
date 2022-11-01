package com.methodpackage.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

public class Read_json_demo {

    public static void parseJonFile() throws IOException {
        try {
            //读取json文件并转化为list<Map>格式
            File file = new File("src/test/resources/api-online.json");
            String jsonString = FileUtils.readFileToString(file, "UTF-8");
            //jsonString结果不是json数组格式的字符串这里需要拼接成json数组格式的字符串
            String  jsonArrayString = String.format("%s%s%s", "[",jsonString,"]");
            System.out.println("jsonArrayString:"+jsonArrayString);
            List<Map> list =  JSON.parseArray(jsonArrayString, Map.class);

            System.out.println("list:"+list);
//            for (Map map : list){
//                String type = String.valueOf(map.get("API_"+"Index"));
//                System.out.println("type:"+type);
//                Map<String,Object> group  = new HashMap<String,Object>();
//                List<Map> listChilds =  JSON.parseArray(jsonArrayString, Map.class);
//
//                for (Map<String, Object> map2 : listChilds) {
//                    String childs = String.valueOf(map2.get("apiPath"));
//                    System.out.println("childs:" + childs);
//                }
//            }
//            List<Map<String,Object>> roleGroups = new ArrayList<>();
//            List<Map<String,Object>> roles = new ArrayList<>();
            for (Map map : list) {
//                String type =String.valueOf(map.get("type"));

                String type =String.valueOf(map.get("API_"+"Index"));
                System.out.println("type:"+type);
                HashMap hashMap = JSON.parseObject(type, HashMap.class);
                System.out.println("hashMap:"+hashMap);
                String value = hashMap.get("apiPath").toString();
                System.out.println("value:"+value);
//                if("API_Index".equals(type)) {
//                    System.out.println("1111");
//                    Map<String, Object> group = new HashMap<String, Object>();
//                    group.put("type", map.get("type").toString());
//                    roleGroups.add(group);
//                    if (map.containsKey("childs")) {
//                        String childs = String.valueOf(map.get("childs"));
//                        List<Map> listChilds = JSON.parseArray(childs, Map.class);
//                        for (Map<String, Object> map2 : listChilds) {
//                            Map<String, Object> roleMap = new HashMap<String, Object>();
//                            roleMap.put("apiName", map2.get("apiName").toString());
//                            roleMap.put("apiPath", map2.get("apiPath").toString());
//                            roleMap.put("apiContentType", map2.get("apiContentType").toString());
//                            roles.add(roleMap);
//                        }
//                    }
//                }
                }

            System.out.println("list:"+list);
//            System.out.println(roleGroups.toString());
//            System.out.println(roles.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        parseJonFile();
    }

}

