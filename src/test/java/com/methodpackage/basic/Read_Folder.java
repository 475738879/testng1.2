package com.methodpackage.basic;

public class Read_Folder{

    public String read_folder(String name){
        String[] split = name.split("\\.");
        int len1 = split.length-1;
        int len2 = split.length-2;
        String url = split[len2]+"."+split[len1];
        return url;
    }
}
