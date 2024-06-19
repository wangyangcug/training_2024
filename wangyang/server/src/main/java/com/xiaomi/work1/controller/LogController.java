package com.xiaomi.work1.controller;


import com.xiaomi.work1.Factory.LogServiceFactory;
import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.MyRequest;
import com.xiaomi.work1.bean.Result;
import com.xiaomi.work1.service.Imp.LogServiceImpl;
import com.xiaomi.work1.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: LogController
 * Package: com.xiaomi.work1.controller
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 12:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/log")
public class LogController {


    @Resource
    private LogServiceFactory storageFactory;


    /**
     * 上传新增日志
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Result<?> uploadLogs(@RequestBody MyRequest request){

        List<Log> logs = request.getLogs();
        String storageType = request.getStorageType();
        return storageFactory.LogStorage(storageType).uploadLogs(logs);
    }

    /**
     * 查询日志
     * @param hostname
     * @param file
     * @param storageType
     * @return
     */
    @GetMapping("/query")
    public Result<?> queryLogs(@RequestParam String hostname,
                               @RequestParam String file,
                               @RequestParam String storageType){
        return storageFactory.LogStorage(storageType).queryLogs(hostname,file);
    }
}
