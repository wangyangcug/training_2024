package com.example.collector_all.collector;

import com.alibaba.fastjson.JSONArray;
import com.example.collector_all.utils.FileListener;
import com.example.collector_all.utils.FileMonitor;
import com.example.collector_all.utils.LoadConfig;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * ClassName: LogCollector
 * Package: com.example.collector_all.collector
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/4 15:27
 * @Version 1.0
 */
@Slf4j
public class LogCollector {
//    private final static String SERVER_URL="http://172.27.72.128:8080/api/metric/upload";
    private final static String SERVER_URL="http://localhost:8080/api/log/upload";
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
     * 从本地暂存文件中读取封装好的json数组并发送给服务端
     */
//    FileListener listener = new FileListener();
    public void collectorLog(){
        //读取json数组
        JSONArray logs=readJsonFile();
        //发送到服务端
        if(logs!=null){
            sendLogToServer(logs);
        }


        //清除已有信息
        FileListener.clear();

        System.out.println( FileListener.getJsonArray());
    }

    private void sendLogToServer(JSONArray logs) {
        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            // 发送 JSON 数据
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = logs.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取响应
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray readJsonFile() {
//        // JSON 文件路径
//        String filePath = "D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\tmp.json";
//        JSONArray jsonArray=null;
//        try {
//            // 使用 ObjectMapper 读取 JSON 文件
//            ObjectMapper objectMapper = new ObjectMapper();
//            jsonArray = objectMapper.readValue(new File(filePath), JSONArray.class);
//
//            // 打印 JSONArray
//            System.out.println("JSONArray:");
//            System.out.println(jsonArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return jsonArray;

        JSONArray tmpjson = FileListener.getJsonArray();
        log.info("哈哈");

        if(!tmpjson.isEmpty()){
            return tmpjson;
        }else {
            return new JSONArray();
        }
    }
}
