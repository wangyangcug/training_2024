package com.xiaomi.work1;

import com.xiaomi.work1.bean.Log;
import com.xiaomi.work1.dao.EsLogRepository;
import com.xiaomi.work1.dao.EsLogRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
public class ElasticsearchTemplateTest {

    @Resource
    EsLogRepository esLogRepository;

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    void save() {
        Log log = new Log();
        log.setId(1);
        log.setHostname("aaa");
        log.setFile("bbb");
        log.setLogs(new String[]{"111","222"});
        System.out.println(esLogRepository.save(log));
    }
}