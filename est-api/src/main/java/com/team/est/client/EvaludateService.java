package com.team.est.client;

import com.team.est.dto.EvaluateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "EST-PROVIDER")

public interface EvaludateService {
    @RequestMapping("/evaluate/assess")
    public EvaluateDto assess(@RequestParam("modelId") Long modelId,
                              @RequestParam("optionIds") String optionIds,
                              @RequestParam("userid") Long userid);

    @RequestMapping("/evaluate/getEvaluateId/{evaluateId}")
    public EvaluateDto  getEvaluateId(@PathVariable("evaluateId") Long evaluateId);


}
