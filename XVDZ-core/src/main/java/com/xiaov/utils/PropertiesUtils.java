package com.xiaov.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Sharkseven on 2016/7/23.
 */
public class PropertiesUtils {

    private static  Properties properties;

    static {

       properties=new Properties();
        try {
            String path = PropertiesUtils.class.getClassLoader().getResource("config.properties").getPath();
            properties.load(new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String  getValue(String key){
        if(StrKit.notBlank(key)){
            return properties.getProperty(key,"");
        }else{
            return null;
        }
    }
}
