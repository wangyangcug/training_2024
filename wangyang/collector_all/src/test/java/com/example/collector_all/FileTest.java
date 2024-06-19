package com.example.collector_all;

import com.alibaba.fastjson.JSONArray;
import com.example.collector_all.collector.LogCollector;
import com.example.collector_all.Listener.FileListener;
import com.example.collector_all.Listener.FileMonitor;
import com.example.collector_all.utils.LoadConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: FileTest
 * Package: com.example.collector_all
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/3 16:49
 * @Version 1.0
 */
public class FileTest {
    @Test
    void test1() throws Exception {
        FileMonitor fileMonitor = new FileMonitor(5000);
        fileMonitor.monitor("D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\test",new FileListener());
        fileMonitor.start();
        TimeUnit.HOURS.sleep(1);

    }
    @Test
    void test2(){
        LoadConfig loadConfig = new LoadConfig();
        Map<String, Object> mp = loadConfig.parseJsonFile("D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\cfg.json");
        JSONArray files = (JSONArray)mp.get("files");
        String storage = (String)mp.get("log_storage");
        for (Object file : files) {
            System.out.println(file.toString());
        }
        System.out.println(storage);

    }

    @Test
    void test3() throws Exception {
        LogCollector collector = new LogCollector();
        collector.logCollector("D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\cfg.json");
        TimeUnit.MINUTES.sleep(1);
        collector.collectorLog();
    }

    /**
     * 测试读取json文件，返回json数组类型
     * @throws Exception
     */
    @Test

    void test4() throws Exception {
        LogCollector collector = new LogCollector();
        System.out.println("哈哈哈");
        collector.readJsonFile();
    }

    /**
     * 测试colletorLog方法
     * @throws Exception
     */
    @Test
    void test5() throws Exception {
        LogCollector collector = new LogCollector();
        collector.collectorLog();
    }

    /**
     * 测试从集合中读取变化的日志信息并上传到服务器，最后清除集合信息
     * @throws Exception
     */
    @Test
    void test6() throws Exception {
        LogCollector collector = new LogCollector();
        collector.collectorLog();
    }

    /**
     * 测试使用不同的策略存储
     * @throws Exception
     */
    @Test
    void test7() throws Exception {
        LogCollector collector = new LogCollector();
        String filePath = "D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\tmp.json";

        // 使用 ObjectMapper 读取 JSON 文件
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray logs = objectMapper.readValue(new File(filePath), JSONArray.class);
//        String storage = "elasticsearch";
        String storage = "local_file";
//        String storage = "mysql";
        collector.sendLogToServer( logs,  storage);
    }
}
