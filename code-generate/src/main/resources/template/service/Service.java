package $

{package_service};
import ${package_pojo}.${Table};
import com.github.pagehelper.PageInfo;

import java.util.*;

/****
 * @Author:唐嘉
 * @Description:${Table}业务层接口
 * @Date ${now}
 *****/
public interface $ {
    Table
}

    Service {

        /***
         * ${Table}多条件分页查询
         * @param searchMap
         * @param page
         * @param size
         * @return
         */
        PageInfo<$ {
            Table
        }>findPage(Map < String, Object > searchMap, int page, int size);


        /***
         * 删除${Table}
         * @param id
         */
        void delete ($ {
            keyType
        } id);

        /***
         * 修改${Table}数据
         * @param ${table}
         */
        void update ($ {
            Table
        } $ {
            table
        });

        /***
         * 新增${Table}
         * @param ${table}
         */
        void add ($ {
            Table
        } $ {
            table
        });

        /**
         * 根据ID查询${Table}
         * @param id
         * @return
         */
        $ {
            Table
        } findById($ {
            keyType
        } id);

    }
