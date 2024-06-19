package com.example.collector_all.Listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.security.PrivateKey;

/**
 * ClassName: FileMonitor
 * Package: com.example.collector_all.utils
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/3 16:28
 * @Version 1.0
 */
@Slf4j
public class FileMonitor {
    private FileAlterationMonitor monitor;



    public FileMonitor(long interval) {
        monitor=new FileAlterationMonitor(interval);
    }

    /**
     *
     * @param path 文件路径
     * @param listener 文件监听器
     */
    public void monitor(String path, FileAlterationListener listener){
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    /**
     * 停止监听
     * @throws Exception
     */
    public void stop() throws  Exception{
        monitor.stop();
    }

    /**
     * 启动监听
     * @throws Exception
     */
    public void start() throws Exception{
        monitor.start();
        log.info("启动监听");
    }
}
