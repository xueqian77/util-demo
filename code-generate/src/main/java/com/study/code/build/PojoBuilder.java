package com.study.code.build;

import java.util.Map;

/****
 * @Author:唐嘉
 * @Description:Pojo构建
 * @Date 2019/6/14 19:13
 *****/
public class PojoBuilder {


    /***
     * 构建Pojo
     * @param dataModel
     */
    public static void builder(Map<String, Object> dataModel) {
        //生成Pojo层文件
        BuilderFactory.builder(dataModel,
                "/template/pojo",
                "Pojo.java",
                TemplateBuilder.PACKAGE_POJO,
                ".java");
    }

}
