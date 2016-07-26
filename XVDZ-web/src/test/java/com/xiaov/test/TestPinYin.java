package com.xiaov.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaov.model.Base64Model;
import com.xiaov.model.OrderDetail;
import com.xiaov.model.Orders;
import com.xiaov.model.Product;
import com.xiaov.utils.Pinyin4jUtil;
import com.xiaov.utils.PropertiesUtils;
import com.xiaov.utils.StrKit;
import com.xiaov.utils.StringFileToolkit;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestPinYin {
    public static void main(String[] args) {

        String str="{" +
                "            \"aDesignImgsBase64\": [" +
                "            \"data:image/png;base64,gECBAgQ\"," +
                "                    \"data:image/png;base64,iVBORw0KGg\"" +
                "            ]," +
                "            \"aFullImgsBase64\": [" +
                "            \"data:image/png;base64,gECBAgQ\"," +
                "                    \"data:image/png;base64,iVBORw0KGg\"" +
                "            ]" +
                "        }";

        File file=new File("D:/test.json");
        String base64 = StringFileToolkit.file2String(file, "utf-8");
        base64= URLDecoder.decode(base64);
        Gson gson=new Gson();
        Base64Model base64Model = gson.fromJson(base64, Base64Model.class);

        base64=URLDecoder.decode(base64);
        String path =  PropertiesUtils.getValue("file.path") +"/base64" + "/";

        String [] strs=base64Model.getaDesignImgsBase64();
        String name;
        for ( int i=0;i<strs.length;i++){
            name= UUID.randomUUID().toString()+".png";
            if(StrKit.notBlank(strs[i])){
                file=new File(path+name);
                StringFileToolkit.GenerateImage(strs[i].split("[,]")[1],file);
            }
            if(i==0){
            }else{
            }
        }
        strs=base64Model.getaFullImgsBase64();
        for ( int i=0;i<strs.length;i++){
            name=UUID.randomUUID().toString()+".png";
            if(StrKit.notBlank(strs[i])){
                file=new File(path+name);
                StringFileToolkit.GenerateImage(strs[i].split("[,]")[1],file);
            }
            if(i==0){
            }else{
            }
        }

        System.out.print("www");
    }


}
