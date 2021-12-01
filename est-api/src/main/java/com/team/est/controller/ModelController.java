package com.team.est.controller;

import com.team.est.client.ModelService;
import com.team.est.dto.ModelDto;
import com.team.est.req.ModelReq;
import com.team.est.util.Page;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/model/")

public class ModelController {


    @Autowired(required = false)
    private ModelService modelService;

    @RequestMapping("search")
    public Result<Page<ModelDto>> search(ModelReq modelReq){
        System.out.println("获取编号:"+modelReq.getBrandId());
        //modelReq获取前端传递的请求参数
        //调用服务
        Page<ModelDto> modelDtoPage=modelService.searchModel(modelReq);
        return new Result<>("0","成功",modelDtoPage);
    }

    //编写后台API接口实现商品详情
    @RequestMapping("{id}/detail")
    public Result<ModelDto> detail(@PathVariable("id") Long id){
        //调用远程的服务
        ModelDto modelDto=this.modelService.getModelById(id);
        return new Result<>("0","成功",modelDto);
    }

}
