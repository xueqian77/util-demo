package $

{package_controller};
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import com.github.pagehelper.PageInfo;
import com.study.response.CommonCode;
import com.study.response.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/****
 * @Author:唐嘉
 * @Description:
 * @Date ${now}
 *****/
@RestController
@RequestMapping("/${table}")
public class $ {
    Table
}

    Controller {

    @Autowired
    private $ {
            Table
        } Service $ {
            table
        } Service;

        /***
         * ${Table}分页条件搜索实现
         * @param searchMap
         * @param page
         * @param size
         * @return
         */
        @PostMapping(value = "/search/{page}/{size}")
        public ResultJson findPage (@RequestBody(required = false) Map < String, Object > searchMap,
        @PathVariable int page, @PathVariable int size){
            //调用${Table}Service实现分页条件查询${Table}
            PageInfo<$ {
                Table
            }>pageInfo = $ {
                table
            } Service.findPage(searchMap, page, size);
            return new ResultJson(CommonCode.SUCCESS, pageInfo);
        }


        /***
         * 根据ID删除${Table}数据
         * @param id
         * @return
         */
        @GetMapping(value = "/delete/{id}")
        public ResultJson delete (@PathVariable $ {
            keyType
        } id){
            //调用${Table}Service实现根据主键删除
            $ {
                table
            } Service.delete(id);
            return new ResultJson(CommonCode.SUCCESS, "删除成功");
        }

        /***
         * 修改${Table}数据
         * @param ${table}
         * @param id
         * @return
         */
        @PostMapping(value = "/update/{id}")
        public ResultJson update (@RequestBody $ {
            Table
        } $ {
            table
        },@PathVariable $ {
            keyType
        } id){
            //设置主键值
            $ {
                table
            }.$ {
                keySetMethod
            } (id);
            //调用${Table}Service实现修改${Table}
            $ {
                table
            } Service.update($ {
                table
            });
            return new ResultJson(CommonCode.SUCCESS, "修改成功");
        }

        /***
         * 新增${Table}数据
         * @param ${table}
         * @return
         */
        @PostMapping("/add")
        public ResultJson add (@RequestBody $ {
            Table
        } $ {
            table
        }){
            //调用${Table}Service实现添加${Table}
            $ {
                table
            } Service.add($ {
                table
            });
            return new ResultJson(CommonCode.SUCCESS, "添加成功");
        }

        /***
         * 根据ID查询${Table}数据
         * @param id
         * @return
         */
        @GetMapping("/findById/{id}")
        public ResultJson findById (@PathVariable $ {
            keyType
        } id){
            //调用${Table}Service实现根据主键查询${Table}
            $ {
                Table
            } $ {
                table
            } =$ {
                table
            } Service.findById(id);
            return new ResultJson(CommonCode.SUCCESS, $ {
                table
            });
        }
    }
