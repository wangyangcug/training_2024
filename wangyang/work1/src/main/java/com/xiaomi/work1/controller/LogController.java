package com.xiaomi.work1.controller;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.Result;
import com.xiaomi.work1.service.LogService;
import jakarta.annotation.Resource;
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
    private LogService logService;

    @PostMapping("/upload")
    public Result<?> uploadLogs(@RequestBody List<Log> logs){
        return logService.uploadLogs(logs);
    }


    @GetMapping("/query")
    public Result<?> queryLogs(@RequestParam String hostname,
                               @RequestParam String file){
        return logService.queryLogs(hostname,file);
    }
}
