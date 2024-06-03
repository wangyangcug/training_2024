package com.example.collector_all.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LoadConfig
 * Package: com.example.collector_all.utils
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/3 19:22
 * @Version 1.0
 */

public class LoadConfig {

    private final Map<String,Object> infoMap = new HashMap<>();

    /**
     * 加载配置文件
     * @param path 配置文件路径
     * @return 包含配置文件的集合
     */
     public Map<String,Object> parseJsonFile(String path){
         JsonUtil jsonUtil = new JsonUtil();
         String s = jsonUtil.readJsonFile(path);
         JSONObject jsonObject=JSON.parseObject(s);
         infoMap.put("files",jsonObject.getJSONArray("files"));
         infoMap.put("log_storage",jsonObject.get("log_storage"));
         return infoMap;

     }


}
