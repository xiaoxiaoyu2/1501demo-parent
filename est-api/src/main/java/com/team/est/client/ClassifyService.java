package com.team.est.client;

import com.team.est.dto.ClassifyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "EST-PROVIDER")
public interface ClassifyService {
    //一个接口方法对应一个服务
    @RequestMapping("/classify/getClassifyTree")
    public List<ClassifyDto> getClassifyTree();

}
