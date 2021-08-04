package $

{package_service_impl};
import ${package_mapper}.${Table}Mapper;
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/****
 * @Author:唐嘉
 * @Description:${Table}业务层接口实现类
 * @Date ${now}
 *****/
@Service
public class $ {
    Table
}ServiceImpl implements ${Table}Service{

@Autowired
private ${Table}Mapper ${table}Mapper;


/**
 * ${Table}条件+分页查询
 *
 * @param searchMap 查询条件
 * @param page 页码
 * @param size 页大小
 * @return 分页结果
 */
@Override
public PageInfo<${Table}>findPage(Map<String, Object> searchMap,int page,int size){
        PageHelper.startPage(page,size);
        return new PageInfo<${Table}>(${table}Mapper.select${Table}ByExample(searchMap));
        }


/**
 * 删除
 *
 * @param id
 */
@Override
public void delete(${keyType}id){
        ${table}Mapper.deleteByPrimaryKey(id);
        }

/**
 * 修改${Table}
 * @param ${table}
 */
@Override
public void update(${Table}${table}){
        ${table}Mapper.updateByPrimaryKeySelective(${table});
        }

/**
 * 增加${Table}
 * @param ${table}
 */
@Override
public void add(${Table}${table}){
        ${table}Mapper.insertSelective(${table});
        }

/**
 * 根据ID查询${Table}
 * @param id
 * @return
 */
@Override
public ${Table}findById(${keyType}id){
        return ${table}Mapper.selectByPrimaryKey(id);
        }


        }
