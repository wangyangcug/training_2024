package com.xiaomi.work1.Factory;

import com.xiaomi.work1.service.Imp.EsLogServiceImpl;
import com.xiaomi.work1.service.Imp.LocalLogImpl;
import com.xiaomi.work1.service.Imp.LogServiceImpl;
import com.xiaomi.work1.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * ClassName: LogServiceFactory
 * Package: Factory
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/17 13:26
 * @Version 1.0
 */
@Service
public class LogServiceFactory {
    @Resource
    private LogServiceImpl mysqlStorage;
    @Resource
    private EsLogServiceImpl esStorage;
    @Resource
    private LocalLogImpl localStorage;
    public LogService LogStorage(String storageType) {
        return switch (storageType) {
            case "mysql" -> mysqlStorage;
            case "elasticsearch" -> esStorage;
            case "local_file" -> localStorage;
            default -> throw new IllegalArgumentException("Invalid storage type");
        };
    }
}
