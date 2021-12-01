package com.team.est.controller;

import com.team.est.client.OrderService;
import com.team.est.client.UserService;
import com.team.est.dto.UserDto;
import com.team.est.req.AppointmentReq;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//编写后台接口
@RestController
@RequestMapping("/api/order/")

public class OrderController {


    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private OrderService orderService;

    //预约下单
    @RequestMapping("maintain/submit")
    public Result submit(AppointmentReq appointmentReq,
                         Long evaluateId,
                         HttpServletRequest request){
        //1.获取token
        String token=request.getHeader("token");
        UserDto userDto=userService.getUserInfo(token);
        if(userDto==null){
            return new Result("2001","没有登入");
        }
        //2.调用预约的服务
        try {
            String orderNo=orderService.submit(appointmentReq,evaluateId,userDto.getId());
            HashMap<String,String> map=new HashMap();
            map.put("orderNo",orderNo);
            return new Result("0","成功",map);
        }catch (Exception ex){
            return new Result("1","预约失败，网络异常");
        }
    }
    /**
     * springmvc支持自动转换日期类型的转换
     */
    @InitBinder //springmvc的初始化日期绑定,进控制器之前执行
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
