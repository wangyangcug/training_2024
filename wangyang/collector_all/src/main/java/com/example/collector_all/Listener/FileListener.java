package com.example.collector_all.Listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.*;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: FileListener
 * Package: com.example.collector_all.utils
 * Description:文件监听器
 *
 * @Author WangYang
 * @Create 2024/6/3 16:26
 * @Version 1.0
 */
@Data
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {

//    private static FileListener listener=new FileListener();
    // 暂时存放每个文件上一次的已经读取了的指针位置
    private static Map<String,Long> filePointers = new HashMap<>();
    // 记录文件指针位置的文件名
    private static final String RECORD_FILE = "src/main/resources/filePointers_record.txt";

    //临时Json数组
    public static JSONArray tmpjson=new JSONArray();

    //根据文件名创建对象
    public static Map<String,JSONObject> tmpObject = new HashMap<>();
    private  String JsonPath;

    public FileListener(String jsonPath) {
        JsonPath = jsonPath;
    }




    public  FileListener (){

    }
    public static void setTmpJson(){

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
        //从文件中加载文件以及对应的指针到集合
        loadRecords();
        //若集合里没有文件名称，则证明第一次改动，将文件名称加入到集合中，有的话则拿到上一次读取到的位置
        if(filePointers.containsKey(path)){
            tempPosition=filePointers.get(path);
        }else{
            filePointers.put(path,0L);
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
                //将新增内容封装到json数组,先判断tmpObject集合里是否有该对象，如果有，则直接封装新增日志内容到json数组对应的对象中，
                // 如果没有创建jsonobject对象，放入集合中，并为对象设置值加入到json数组中。
                if(tmpObject.containsKey(path)){
                    JSONObject jsonObject = tmpObject.get(path);
                    JSONArray logs =(JSONArray)jsonObject.get("logs");
                    logs.add(str);
                    jsonObject.put("logs",logs);
                }else{
                    JSONObject object = new JSONObject();
                    String ip = InetAddress.getLocalHost().getHostAddress();
                    object.put("hostname",ip);
                    object.put("file",path);
                    object.put("logs",new JSONArray(Collections.singletonList(str)));
                    tmpObject.put(path,object);
                    tmpjson.add(object);
                }
//                System.out.println(tmpjson);

                //更新文件记录到的位置
                filePointers.put(path,randomAccessFile.getFilePointer());
                //将集合内容写入文件
                writeRecord(filePointers,RECORD_FILE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 将当前的文件指针位置写入文件
     *
     * @param filePointers
     * @param recordFile
     */
    private void writeRecord(Map<String, Long> filePointers, String recordFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(recordFile))) {
            for (Map.Entry<String, Long> entry : filePointers.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("文件指针信息成功写入到 " + recordFile);
        } catch (IOException| NumberFormatException e) {
            System.err.println("读取记录文件时出错：" + e.getMessage());
        }
    }

    /**
     * 从文件中读取文件的指针位置
     */
    private void loadRecords() {
        filePointers.clear(); // 清空现有映射

        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String fileName = parts[0];
                    long pointer = Long.parseLong(parts[1]);
                    filePointers.put(fileName, pointer);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("读取记录文件时出错：" + e.getMessage());
        }
    }

    public static void clear(){
       tmpjson=new JSONArray();;
       tmpObject=new HashMap<>();
    }

    public static JSONArray getJsonArray(){
        return tmpjson;
    }


}
