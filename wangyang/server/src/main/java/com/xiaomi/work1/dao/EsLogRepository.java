package com.xiaomi.work1.dao;

/**
 * ClassName: EsLogRepository
 * Package: com.xiaomi.work1.mapper
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/17 16:39
 * @Version 1.0
 */

import com.xiaomi.work1.bean.Log;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EsLogRepository extends ElasticsearchRepository<Log, String> {
//    @Highlight(
//            fields = {@HighlightField(name = "hostname"), @HighlightField(name = "file")},
//            parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
//    )
//    List<Log> findByHostnameFile(String hostname, String file);
}


