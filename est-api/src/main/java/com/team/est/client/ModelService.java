package com.team.est.client;

import com.team.est.dto.ModelDto;
import com.team.est.req.ModelReq;
import com.team.est.util.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EST-PROVIDER")
public interface ModelService {

    @RequestMapping("/model/searchModel")
    public Page<ModelDto> searchModel(@RequestBody ModelReq modelReq);

    @RequestMapping("/model/getModel/{modelId}")
    public ModelDto getModelById(@PathVariable(value = "modelId") Long modelId);
}
