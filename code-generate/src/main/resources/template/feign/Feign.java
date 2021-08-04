package com.changgou.content.feign;

import com.study.response.ResultJson;
import ${package_pojo}.${Table};
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:唐嘉
 * @Description:
 * @Date ${now}
 *****/
@FeignClient(name = "${serviceName}")
@RequestMapping("/${table}")
public interface $ {
    Table
}

    Feign {


        /***
         * 根据ID删除品牌数据
         * @param id
         * @return
         */
        @GetMapping(value = "/delete/{id}")
        ResultJson delete (@PathVariable $ {
            keyType
        } id);

        /***
         * 修改${Table}数据
         * @param ${table}
         * @param id
         * @return
         */
        @PostMapping(value = "/update/{id}")
        ResultJson update (@RequestBody $ {
            Table
        } $ {
            table
        },@PathVariable $ {
            keyType
        } id);

        /***
         * 新增${Table}数据
         * @param ${table}
         * @return
         */
        @PostMapping("/add")
        ResultJson add (@RequestBody $ {
            Table
        } $ {
            table
        });

        /***
         * 根据ID查询${Table}数据
         * @param id
         * @return
         */
        @GetMapping("/findById/{id}")
                ResultJson<$ {
            Table
        }>findById(@PathVariable $ {
            keyType
        } id);


    }
