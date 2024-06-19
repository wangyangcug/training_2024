package com.xiaomi.work1;

import com.xiaomi.work1.bean.Log;
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

//    @Resource
    EsLogRepository esLogRepository=new EsLogRepository() {
        @Override
        public Page<Log> searchSimilar(Log entity, String[] fields, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Log> S save(S entity, RefreshPolicy refreshPolicy) {
            return null;
        }

        @Override
        public <S extends Log> Iterable<S> saveAll(Iterable<S> entities, RefreshPolicy refreshPolicy) {
            return null;
        }

        @Override
        public void deleteById(Integer integer, RefreshPolicy refreshPolicy) {

        }

        @Override
        public void delete(Log entity, RefreshPolicy refreshPolicy) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers, RefreshPolicy refreshPolicy) {

        }

        @Override
        public void deleteAll(Iterable<? extends Log> entities, RefreshPolicy refreshPolicy) {

        }

        @Override
        public void deleteAll(RefreshPolicy refreshPolicy) {

        }

        @Override
        public <S extends Log> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Log> Iterable<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Log> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<Log> findAll() {
            return null;
        }

        @Override
        public Iterable<Log> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Log entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Log> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public Iterable<Log> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Log> findAll(Pageable pageable) {
            return null;
        }
    };

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