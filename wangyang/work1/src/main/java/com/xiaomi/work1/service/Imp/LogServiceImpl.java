package com.xiaomi.work1.service.Imp;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.Result;
import com.xiaomi.work1.mapper.LogMapper;
import com.xiaomi.work1.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: LogServiceImpl
 * Package: com.xiaomi.work1.service.Imp
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 12:58
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;
    @Override
    public Result<?> uploadLogs(List<Log> logs) {

        for (Log log : logs) {

            logMapper.saveLogs(log);
        }
        //{200,"ok,null}
        return Result.Ok();
    }

    @Override
    public Result<?> queryLogs(String hostname, String file) {
        List<Log> logs = logMapper.queryAll(hostname, file);

//        List<String> all_logs=new ArrayList<>();
//        for (Log logg : logs) {
//            String[] oneLogs = logg.getLogs();
//            all_logs.addAll(Arrays.asList(oneLogs));
//        }
//       String[] result_logs = all_logs.toArray(String[]::new);
////        String[] strings = all_logs.toArray(new String[0]);
//        Log log = new Log();
//        log.setHostname(hostname);
//        log.setFile(file);
//        log.setLogs(result_logs);
        return Result.Ok(logs);
    }
}
