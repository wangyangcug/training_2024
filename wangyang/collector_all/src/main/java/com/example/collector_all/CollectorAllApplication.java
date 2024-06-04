package com.example.collector_all;

import com.example.collector_all.collector.LogCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class CollectorAllApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CollectorAllApplication.class, args);

        LogCollector collector = new LogCollector();
        collector.logCollector("D:\\training_2024\\wangyang\\collector_all\\src\\main\\resources\\cfg.json");


    }

}
