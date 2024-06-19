package com.xiaomi.work1.bean;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ClassName: Log
 * Package: com.xiaomi.work1.bean
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/5 21:38
 * @Version 1.0
 */
@Data
@Table(name = "loglog")
@Document(indexName = "logs")
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


    @Override
    public String toString() {
        return "Log{" +
                "hostname='" + hostname + '\'' +
                ", file='" + file + '\'' +
                ", logs=" + Arrays.toString(logs) +
                '}';
    }
}
