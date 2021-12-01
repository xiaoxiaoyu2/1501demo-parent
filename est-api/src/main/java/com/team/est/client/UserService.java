package com.team.est.client;

import com.team.est.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "EST-PROVIDER")
public interface UserService {
    /**
     * 实现用户登入的服务
     * @param phone
     * @param password
     * @return  token随机字符串
     */
    @RequestMapping("/user/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password);

    /**
     * 通过用户的token获取信息
     * @param token
     * @return  用户信息
     */
    @RequestMapping("/user/getUserInfo")
    public UserDto getUserInfo(@RequestParam(value = "token") String token);

    @RequestMapping("/user/phonelogin")
    public String phonelogin(@RequestParam("phone") String phone);

}
