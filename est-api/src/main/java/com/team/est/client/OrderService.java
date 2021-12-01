package com.team.est.client;

import com.team.est.req.AppointmentReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "EST-PROVIDER")

public interface OrderService {

    @RequestMapping("/order/submit")
    public String submit(
            @RequestBody AppointmentReq appointmentReq,
            @RequestParam("evaluateId") Long evaluateId,
            @RequestParam("userId") Long userId);

}
