package com.mi.elasticsearch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mi.elasticsearch.common.Tool;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mi.elasticsearch.dao.InfoRepository;
import com.mi.elasticsearch.entity.Info;
import com.mi.elasticsearch.service.InfoService;

import static java.lang.Thread.sleep;

/**
 * 日志Service实现类
 *
 * @author MichaelShi
 * @email gyechi163.com
 * @date 2019年8月13日 上午9:47:01
 */
@Service("InfoService")
public class InfoServiceImpl implements InfoService {
    @Autowired
    @Qualifier("InfoRepository")
    InfoRepository infoRepository;

    @Override
    public Optional<Info> findById(String id) {
        return infoRepository.findById(id);
    }

    @Override
    public void save() {
        List<Info> list = new ArrayList<>();
        while (list.size() < 100) {
            String num = Tool.cvt(list.size(), true);
            list.add(new Info(Tool.getUuid(), Tool.getUuid(), "你爹是我" + num + "号", "爸爸" + num + "father" + list.size(), "爸爸" + num + "father" + list.size(), "爸爸" + num + "father" + list.size(), "你爹是我" + num + "号", "你爹是我" + num + "号", "你爹是我" + num + "号", num + ".jpg", new Date()));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }
        infoRepository.saveAll(list);
    }

    @Override
    public void delete(Info Info) {
        infoRepository.delete(Info);
    }

    @Override
    public Optional<Info> findOne(String id) {
        return infoRepository.findById(id);
    }

    @Override
    public Iterable<Info> findAll() {
        return infoRepository.findAll();
    }

    @Override
    public Page<Info> findInfo(Info info, int page, int size) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.wildcardQuery("title", "*" + info.getTitle() + "*"));
        boolQueryBuilder.should(QueryBuilders.wildcardQuery("text", "*" + info.getTitle() + "*"));
        return infoRepository.search(boolQueryBuilder, PageRequest.of(page, size, Sort.Direction.DESC, "_score", "date"));
    }

    @Override
    public Page<Info> findByCsz(String csz, PageRequest pageRequest) {
        /*	return infoRepository.findByCsz(csz, pageRequest);*/
        return null;
    }

    @Override
    public Iterable<Info> getAllByInfoTime(String csz) {
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title", csz));
        return infoRepository.search(query);
    }
}
