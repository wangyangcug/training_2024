package com.example.collector_all.collector;

import com.alibaba.fastjson.JSONArray;
import com.example.collector_all.utils.FileListener;
import com.example.collector_all.utils.FileMonitor;
import com.example.collector_all.utils.LoadConfig;

import java.io.File;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: LogCollector
 * Package: com.example.collector_all.collector
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/4 15:27
 * @Version 1.0
 */
public class LogCollector {
    //TODO:加载配置文件，获取需要收集的日志文件配置文件监听器

    public void logCollector(String path) throws Exception {
        LoadConfig loadConfig = new LoadConfig();
        Map<String, Object> jsonFile = loadConfig.parseJsonFile(path);
        JSONArray files = (JSONArray)jsonFile.get("files");
        //默认所有文件都放在一个目录下，获取第一个文件的文件夹路径即可
        String fileFolder="";
        for (Object file : files) {
            fileFolder=file.toString();
            break;
        }
        String folderPath = new File(fileFolder).getParent();
        String storage = (String)jsonFile.get("log_storage");

        //根据日志文件配置文件监听器
        ListenerFile(folderPath,path,1000L);

    }

    private void ListenerFile(String folderPath,String path, Long interval) throws Exception {
        FileMonitor fileMonitor = new FileMonitor(interval);
        fileMonitor.monitor(folderPath,new FileListener(path));
        fileMonitor.start();
    }

    /**
     * 根据策略进行存储日志新增内容（目前写死，后续进行优化
     * @param logs 日志内容
     * @param storage 存储策略
     * @param path 文件名称
     */
    public void savelogs(String logs,String storage,String path){
        if("local_file".equals(storage)){
            System.out.println(logs);
        }else if("mysql".equals(storage)){
            sendLogs(logs,path);
        }
    }

    /**
     * 封装日志新增内容传至服务器上报接口
     * @param logs 存储的日志内容
     * @param path 日志文件名称
     */
    private void sendLogs(String logs,String path) {

    }
}
