package com.mi.elasticsearch.controller;

import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.mi.elasticsearch.common.Tool;
import com.mi.elasticsearch.entity.Info;
import com.mi.elasticsearch.service.InfoService;

/**
 * 日志controller
 *
 * @author MichaelShi
 * @email gyechi163.com
 * @date 2019年8月13日 上午9:53:53
 */
@RestController
@RequestMapping("info")
public class InfoController {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    InfoService infoService;

    @RequestMapping("/{id}")
    public Info getBookById(@PathVariable String id) {
        Optional<Info> opt = infoService.findById(id);
        Info info = null;
        if (!opt.toString().contains("Optional.empty")) {
            info = opt.get();
        }
        System.out.println(info);
        return info;
    }

    @RequestMapping("/search/{page}/{size}")
    public Page<Info> getInfo(@RequestBody Info info, @PathVariable int page, @PathVariable int size) {
        Page<Info> opt = infoService.findInfo(info, page, size);
        return opt;
    }

    @RequestMapping("/save")
    public void save(HttpServletRequest request) {
        infoService.save();
    }

    @RequestMapping("info/all")
    public String all() {
        return JSON.toJSONString(infoService.findAll());
    }
}
