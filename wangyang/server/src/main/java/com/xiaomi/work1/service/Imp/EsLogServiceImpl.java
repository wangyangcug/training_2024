package com.xiaomi.work1.service.Imp;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.bean.Result;

//import com.xiaomi.work1.dao.EsLogRepository;
import com.xiaomi.work1.dao.EsLogRepository;
import com.xiaomi.work1.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: EsLogServiceImpl
 * Package: com.xiaomi.work1.service.Imp
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/17 13:24
 * @Version 1.0
 */
@Service
public class EsLogServiceImpl implements LogService {

    @Resource
    private EsLogRepository ElasticSearchRepository;

    @Override
    public Result<?> uploadLogs(List<Log> logs) {
        ElasticSearchRepository.saveAll(logs);
        return   Result.Ok();
    }

    @Override
    public Result<?> queryLogs(String hostname, String file) {
        return null;
    }
}
