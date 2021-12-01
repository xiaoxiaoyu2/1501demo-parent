package com.team.est.client;

import com.team.est.dto.MalfunctionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "EST-PROVIDER")
public interface MalfunctionService {

    @RequestMapping("/malfunction/getMalfunction")
    public List<MalfunctionDto> getMalfunction(@RequestParam("modelId") Long modelId);
}
