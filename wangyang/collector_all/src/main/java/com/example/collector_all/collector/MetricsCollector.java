package com.example.collector_all.collector;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.collector_all.metrics.CpuInfo;
import com.example.collector_all.metrics.MemInfo;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName: MetricsCollerctor
 * Package: com.xiaomi.work1.collector
 * Description:
 *
 * @Author WangYang
 * @Create 2024/5/26 16:09
 * @Version 1.0
 */
public class MetricsCollector {
    private final static String SERVER_URL="http://172.27.72.128:8080/api/metric/upload";

    public static void main(String[] args) {
        //创建定时任务定期收集信息并且向服务器发送请求
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    collectMetricsInfo();
//                    System.out.println("发送成功");
                }catch (Exception e){
                    throw new RuntimeException(e);
                }

            }
        },0,60000);

    }
    public static void collectMetricsInfo() throws Exception {
        CpuInfo cpuInfo = new CpuInfo();
        MemInfo memInfo = new MemInfo();
        //获取信息并封装到json对象
        double cpuUse=cpuInfo.getcpuUse();
        double memUse=memInfo.getmemUse();
        String ip = InetAddress.getLocalHost().getHostAddress();

        JSONObject cpuJson = new JSONObject();
        cpuJson.put("metric","cpu.used.percent");
        cpuJson.put("endpoint",ip);
        cpuJson.put("timestamp",System.currentTimeMillis());
        cpuJson.put("step",60);
        cpuJson.put("value",String.format("%.1f",cpuUse));

        JSONObject memJson = new JSONObject();
        memJson.put("metric","mem.used.percent");
        memJson.put("endpoint",ip);
        memJson.put("timestamp",System.currentTimeMillis());
        memJson.put("step",60);
        memJson.put("value",String.format("%.1f",memUse));

        //将获取到的信息装入JSON数组
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(cpuJson);
        jsonArray.add(memJson);
        //将json数组发送到服务器
        sendPost(jsonArray);

    }
    public static void sendPost(JSONArray metrics) throws Exception {

        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            // 发送 JSON 数据
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = metrics.toString().getBytes("utf-8");
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
}
