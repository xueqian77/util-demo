package com.mi.elasticsearch.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mi.elasticsearch.entity.Info;

/**
 * 日志Service接口
 *
 * @author MichaelShi
 * @email gyechi163.com
 * @date 2019年8月13日 上午9:44:39
 */
public interface InfoService {
    Optional<Info> findById(String id);

    void save();

    void delete(Info info);

    Optional<Info> findOne(String id);

    Iterable<Info> findAll();

    Page<Info> findInfo(Info info, int page, int size);

    Page<Info> findByCsz(String csz, PageRequest pageRequest);

    Iterable<Info> getAllByInfoTime(String csz);
}
