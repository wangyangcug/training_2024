package com.xiaomi.work1.service.Imp;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.Result;
import com.xiaomi.work1.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ClassName: LocalServiceImpl
 * Package: com.xiaomi.work1.service.Imp
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/19 13:38
 * @Version 1.0
 */
@Service
@Slf4j
public class LocalLogImpl implements LogService {
    @Override
    public Result<?> uploadLogs(List<Log> logs) {
        String filePath="src/main/resources/log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath,true))) {
            for (Log log : logs) {
                writer.println(log.toString());
            }
            log.info("日志已成功写入文件：" + filePath);
        } catch (IOException e) {
            log.info("写入文件时出错：" + e.getMessage());
        }

        return Result.Ok();
    }

    @Override
    public Result<?> queryLogs(String hostname, String file) {
        return null;
    }
}
