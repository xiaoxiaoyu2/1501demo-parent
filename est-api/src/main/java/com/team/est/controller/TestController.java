package com.team.est.controller;
import com.team.est.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //控制请求--->后台api接口
    @RequestMapping("/getData")
    public Result<String> getData() {
        System.out.println("我实现了添加功能");
        //返回结果-->  统一返回result  List<Student>
        return new Result<String>("200", "添加成功", "data表示服务返回的对象");
    }
}
