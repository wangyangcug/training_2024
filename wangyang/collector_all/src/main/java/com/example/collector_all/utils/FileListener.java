package com.example.collector_all.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
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
