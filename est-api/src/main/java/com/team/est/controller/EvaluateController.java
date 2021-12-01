package com.team.est.controller;

import com.team.est.client.EvaludateService;
import com.team.est.client.UserService;
import com.team.est.dto.EvaluateDto;
import com.team.est.dto.UserDto;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/evaluate/")

public class EvaluateController {

    @Autowired(required = false)
    private EvaludateService evaludateService;
    @Autowired(required = false)
    private UserService userService;


    @RequestMapping("assess")
    public Result<EvaluateDto> assess(Long modelId, String optionIds, HttpServletRequest request){
        //1.token在请求头中
        String token=request.getHeader("token");
        //利用token获取用户id
        UserDto users=userService.getUserInfo(token);
        //2.调用服务实现估价
        EvaluateDto evaluateDto=evaludateService.assess(modelId,optionIds,users.getId());
        return new Result("0","成功",evaluateDto);
    }

    //查询评估信息
    @RequestMapping("{id}/assessInfo")
    public Result<EvaluateDto> assessInfo(@PathVariable("id") Long id){
        //调用远程的服务
        EvaluateDto evaluateDto=this.evaludateService.getEvaluateId(id);
        return new Result<>("0","成功",evaluateDto);
    }


}
