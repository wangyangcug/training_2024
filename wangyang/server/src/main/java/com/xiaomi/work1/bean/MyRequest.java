package com.xiaomi.work1.bean;

import lombok.Data;

import java.util.List;

/**
 * ClassName: MyRequest
 * Package: com.xiaomi.work1.bean
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/19 13:13
 * @Version 1.0
 */
@Data
public class MyRequest {
    private List<Log> logs;
    private String StorageType;
}
