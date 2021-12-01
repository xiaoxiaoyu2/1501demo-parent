package com.team.est.controller;

import com.team.est.util.Result;
import com.team.est.util.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Classname SmsController
 * @Description TODO
 * @Date 2021/8/16 16:08
 * @Created by Administrator
 */
@RestController
@RequestMapping("/api/sms/")
public class SmsController {

    @Autowired
    private RedisTemplate redisTemplate;

    //发送验证码
    @RequestMapping("verifyCode")
    public Result verifyCode(String phone,int codeType){//codeType区分验证码类型不用
        //1.生成随机四位数的验证码
        int code=(int)Math.round((Math.random()+1) * 1000);
        System.out.println("验证码是:"+code);
        //2.将验证发送到手机中
        boolean flag=SendSmsUtil.SendMsg(phone,"SMS_166867011","{\"code\":"+code+"}");
        if(flag){
            //3.为了后期登入比较，所以将验证码保存到redis中   springboot整合redis
            //  手机号是键，值为验证码
            ValueOperations<String,String> vo=this.redisTemplate.opsForValue();
            vo.set("sms:"+phone,code+"",60,TimeUnit.MINUTES);
            return new Result("0","成功");
        }else{
            return new Result("1","获取验证码失败");
        }
    }
}
