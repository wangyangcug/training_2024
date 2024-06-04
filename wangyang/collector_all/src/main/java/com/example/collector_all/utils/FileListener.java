package com.example.collector_all.utils;

import com.alibaba.fastjson.JSONArray;
import com.example.collector_all.collector.LogCollector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * ClassName: FileListener
 * Package: com.example.collector_all.utils
 * Description:文件监听器
 *
 * @Author WangYang
 * @Create 2024/6/3 16:26
 * @Version 1.0
 */
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {

    // 存放每个文件上一次的已经读取了的指针位置
    private static Map<String,Long> lastPosition = new HashMap<>();

    private  String JsonPath;

    public FileListener(String jsonPath) {
        JsonPath = jsonPath;
    }
    public FileListener() {
    }


    /**
     * @param observer
     */
    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);

    }

    /**
     * @param directory
     */
    @Override
    public void onDirectoryCreate(File directory) {

        log.info("新建文件夹："+directory.getAbsolutePath());

    }

    /**
     * @param directory
     */
    @Override
    public void onDirectoryChange(File directory) {

        log.info("修改文件夹："+directory.getAbsolutePath());

    }

    /**
     * @param directory
     */
    @Override
    public void onDirectoryDelete(File directory) {

        log.info("删除文件夹："+directory.getAbsolutePath());
    }

    /**
     * @param file
     */
    @Override
    public void onFileCreate(File file) {

        log.info("创建文件："+file.getAbsolutePath());

    }


    /**
     * @param file
     */
    @Override
    public void onFileChange(File file) {
        log.info("文件变化："+file.getAbsolutePath());
        // TODO:检测新增内容，以指定方式存储
        long tempPosition = 0;
        String path = file.getPath();
        System.out.println(path);
        //若集合里没有文件名称，则证明第一次改动，将文件名称加入到集合中，有的话则拿到上一次读取到的位置
        if(lastPosition.containsKey(path)){
            tempPosition=lastPosition.get(path);
        }else{
            lastPosition.put(path,0L);
        }
        //打开文件并定位到上次记录到的位置
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r")){
            randomAccessFile.seek(tempPosition);

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(randomAccessFile.getFD())))){
                String line;
                StringBuilder str= new StringBuilder();
                while((line=reader.readLine())!=null){
                    // 对新增的内容进行处理
                    str.append(line);
                }
                //获取配置策略
                LoadConfig loadConfig = new LoadConfig();
                Map<String, Object> jsonFile = loadConfig.parseJsonFile(JsonPath);
                String storage = (String)jsonFile.get("log_storage");
                //根据配置的策略进行存储
//                System.out.println(str);
                LogCollector collector = new LogCollector();
                collector.savelogs(str.toString(),storage,path);
                //更新文件记录到的位置
                lastPosition.put(path,randomAccessFile.getFilePointer());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * @param file
     */
    @Override
    public void onFileDelete(File file) {
        log.info("文件删除："+file.getAbsolutePath());
    }

    /**
     * @param observer
     */
    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }
}
