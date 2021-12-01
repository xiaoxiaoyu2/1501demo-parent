package com.team.est.controller;

import com.team.est.client.UserService;
import com.team.est.dto.UserDto;
import com.team.est.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")

public class UserController {
    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;


    @RequestMapping("login/normal")
    public Result<Map<String,String>> noramalLogin(String phone, String password){
        //调用远程服务
        String token = this.userService.login(phone, password);
        if (StringUtils.isEmpty(token)){  //比较token里的内容是否为空,如果是空则登录失败
            return new Result<>("1002","登入失败");
        }else {
            //有token
            Map<String,String> map=new HashMap<>();  //定义返回的对象
            map.put("token",token);
            return new Result<>("0","成功",map);
        }
    }

    //编写获取用户信息的接口
    @RequestMapping("info")
    //public Result<UserDto> info(String token){ // 传token进来 请求?token=?????
    public Result<UserDto> info(HttpServletRequest request){
        //从请求头中获取token
        String token=request.getHeader("token");
        System.out.println("获取客户端中传递的token:"+token);
        //调用服务 获取用户信息
        UserDto userDto=this.userService.getUserInfo(token);
        if(userDto==null){
            return new Result<>("2001","没有登入");
        }else{
            return new Result<>("0","登入啦",userDto);
        }
    }


    //使用手机号实现用户的注册，登入
    @RequestMapping("login/sms")
    public Result<Map<String,String>> sms(String phone,String sms){ //sms输入验证码
        //1.判断验证是否正确
        //1.1从redis中获取验证码
        ValueOperations<String,String> vo=this.redisTemplate.opsForValue();
        if(redisTemplate.hasKey("sms:"+phone)){
            //1.2判断验证码
            if(vo.get("sms:"+phone).equals(sms)){
                //2.如果验证码正确，实现注册,登入
                // 通过手机号实现登入,返回token  ====>调用服务
                String token=this.userService.phonelogin(phone);
                if(StringUtils.isEmpty(token)){  //登入失败
                    return new Result<>("1002","登入失败");
                }else{  //有token
                    Map<String,String> map=new HashMap<>();  //定义返回的对象
                    map.put("token",token);
                    return new Result<>("0","成功",map);
                }
            }
            else {
                return new Result<>("1003", "验证码不正确");
            }
        }
        else{
            return new Result<>("1003","该手机号还没有验证码");
        }
    }
}
