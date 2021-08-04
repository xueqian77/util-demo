package com.mi.elasticsearch.dao;

import com.mi.elasticsearch.entity.Info;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


/**
 * @author MichaelShi
 * @email gyechi163.com
 * @date 2019年8月13日 上午9:43:06
 */
@Component("InfoRepository")
public interface InfoRepository extends ElasticsearchRepository<Info, String> {

}
