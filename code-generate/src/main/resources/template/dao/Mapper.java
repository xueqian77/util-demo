package ${package_mapper};

import ${package_pojo}.${Table};
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/****
 * @Author:唐嘉
 * @Description:${Table}的Mapper
 * @Date ${now}
 *****/
public interface ${Table}Mapper extends Mapper<${Table}>, InsertListMapper<${Table}>{

}
