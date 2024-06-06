package com.xiaomi.work1.bean;


import com.xiaomi.work1.handler.StringArrayTypeHandler;
import com.xiaomi.work1.utils.StringArrayToStringConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Log
 * Package: com.xiaomi.work1.bean
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/5 21:38
 * @Version 1.0
 */

@Table(name = "loglog")
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String hostname;

    private String file;


    private String[] logs;

    public Log( String hostname, String file, String[] logs) {
        this.hostname = hostname;
        this.file = file;
        this.logs = logs;
    }

    public Log( ) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String[] getLogs() {
        return logs;
    }

    public void setLogs(String[] logs) {
        this.logs = logs;
    }
}
