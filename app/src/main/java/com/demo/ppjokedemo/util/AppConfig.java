package com.demo.ppjokedemo.util;

import android.content.res.AssetManager;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.demo.ppjokedemo.model.BottomBar;
import com.demo.ppjokedemo.model.Destination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AppConfig {

    private static HashMap<String,Destination> sDestConfig;
    private static BottomBar sBottomBar;

    public static HashMap<String,Destination> getDestConfig() {

        if (sDestConfig == null) {
            String content = parseFile("destination.json");
            sDestConfig = JSONObject.parseObject(content, new TypeReference<HashMap<String, Destination>>(){}.getType());
        }
        return sDestConfig;
    }

    public static BottomBar getBottomBar() {
        if (sBottomBar == null) {
            String content = parseFile("main_tabs_config.json");
            sBottomBar = JSONObject.parseObject(content,BottomBar.class);
        }
        return sBottomBar;
    }

    private static String parseFile(String fileName) {
        AssetManager assets = AppGlobals.getApplication().getResources().getAssets();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        try{
            inputStream = assets.open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
