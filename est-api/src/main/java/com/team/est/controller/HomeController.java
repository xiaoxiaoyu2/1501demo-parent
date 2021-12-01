package com.team.est.controller;

import com.team.est.client.ClassifyService;
import com.team.est.dto.ClassifyDto;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/home/")

public class HomeController {
    @Autowired(required = false)
    private ClassifyService classifyService;

    @RequestMapping("classifyTree")
    public Result<List<ClassifyDto>>classifyTree(){
    //调用springcloud的eureka远程服务(采用feign组件)
        List<ClassifyDto> list = this.classifyService.getClassifyTree();

    return new Result<List<ClassifyDto>>("0","成功",list);
    }

}
