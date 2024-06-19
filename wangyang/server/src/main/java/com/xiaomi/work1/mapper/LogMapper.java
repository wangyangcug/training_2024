package com.xiaomi.work1.mapper;

import com.xiaomi.work1.bean.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: LogMapper
 * Package: com.xiaomi.work1.mapper
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 13:11
 * @Version 1.0
 */

public interface LogMapper {


    public void saveLogs(Log log);

    List<Log> queryAll(String hostname, String file);
}
