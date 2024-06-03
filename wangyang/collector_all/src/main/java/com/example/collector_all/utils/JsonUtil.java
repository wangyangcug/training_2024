package com.example.collector_all.utils;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: JsonUtil
 * Package: com.example.collector_all.utils
 * Description: json文件工具类用来读取json文件
 *
 * @Author WangYang
 * @Create 2024/6/3 19:31
 * @Version 1.0
 */

public class JsonUtil {
    public  String readJsonFile(String path) {
        StringBuilder JsonStr = new StringBuilder();
        String tempString = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while ((tempString = reader.readLine()) != null) {
                JsonStr.append(tempString);
            }
        } catch (Exception ignored) {
        }

        return JsonStr.toString();

    }
}
