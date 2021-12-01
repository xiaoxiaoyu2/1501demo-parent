package com.team.est.controller;

import com.team.est.client.MalfunctionService;
import com.team.est.dto.MalfunctionDto;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evaluate/")

public class MalfunctionController {

    @Autowired(required = false)
    private MalfunctionService malfunctionService;

    //加载评估的维度
    @RequestMapping("specList")
    public Result<List<MalfunctionDto>> specList(Long modelId){

        //调用远程的服务
        List<MalfunctionDto> malfunctionDtos=malfunctionService.getMalfunction(modelId);
        return new Result<>("0","成功",malfunctionDtos);

    }
}
