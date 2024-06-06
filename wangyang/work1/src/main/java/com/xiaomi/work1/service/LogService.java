package com.xiaomi.work1.service;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.Result;

import java.util.List;

/**
 * ClassName: LogService
 * Package: com.xiaomi.work1.service
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 12:55
 * @Version 1.0
 */
public interface LogService {
    /**
     * 上传日志信息
     * @param logs
     * @return
     */
    Result<?> uploadLogs(List<Log> logs);

    /**
     * 查询日志信息
     * @param hostname
     * @param file
     * @return
     */
    Result<?> queryLogs(String hostname,String file);
}
