package com.example.collector_all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.collector_all.collector.LogCollector;
import com.example.collector_all.utils.FileListener;
import com.example.collector_all.utils.FileMonitor;
import com.example.collector_all.utils.LoadConfig;
import org.junit.jupiter.api.Test;

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
        TimeUnit.HOURS.sleep(1);
    }
}
