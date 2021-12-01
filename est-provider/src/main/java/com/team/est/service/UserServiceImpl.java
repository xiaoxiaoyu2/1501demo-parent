package com.team.est.service;

import com.team.est.dto.UserDto;
import com.team.est.entity.Users;
import com.team.est.entity.UsersExample;
import com.team.est.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user/")   //请求前缀
public class UserServiceImpl {

    @Autowired(required = false)
    private UsersMapper usersMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  编写用户登入的服务
     * @param phone  手机号
     * @param password 密码
     * @return  token标识
     */

    @RequestMapping("login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        //1.获取用户名密码实现用户登入的验证
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria(); //验证user的条件,用户名和密码
        criteria.andAccountEqualTo(phone);
        criteria.andPasswordEqualTo(password);
        List<Users> users=usersMapper.selectByExample(usersExample);//根据条件查询得出一条数据(是list)
        if(users!=null&&users.size()==1){  //←判断得出来的list users里不等于空,并且只有一条数据,表明登录成功
            //登入成功
            //2.创建token(随机字符串)       //↓把uuid自动生成的字符串中的"-" 变为自定义的空格
            String token= UUID.randomUUID().toString().replace("-","");
            //3.将token和用户信息保存到Redis，以便后期使用
            ValueOperations<String,Users> vo=this.redisTemplate.opsForValue();
            vo.set("token:"+token,users.get(0),180, TimeUnit.MINUTES);  //←将token保存到名为token组里面,设置时间为三小时
            //4.返回token
            return token;
        }
        return "";//登入失败
    }

    //通过token获取用户信息
    @RequestMapping("getUserInfo")
    public UserDto getUserInfo(@RequestParam(value = "token") String token){
        ValueOperations<String,Users> vo=this.redisTemplate.opsForValue();
        if(this.redisTemplate.hasKey("token:"+token)){  //存在
            //获取用户的实体信息
            Users users=vo.get("token:"+token);
            //将用户实体转化为用户的DTO
            UserDto userDto=new UserDto(users.getId(),users.getAccount(),users.getUserName(),users.getSex(),users.getFaceUrl());
            //重点:一定要将token保存到实体中，以便后期使用
            userDto.setToken(token);
            return userDto;
        }else{
            return null;//有可能用户没有登入，登入后过期，虚假token
        }
    }

    @RequestMapping("phonelogin")
    public String phonelogin(@RequestParam("phone") String phone){
        //1.获取用户名密码实现用户登入的验证
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andAccountEqualTo(phone);
        List<Users> users=usersMapper.selectByExample(usersExample);
        //如果有记录说明前期该手机号注册过，有用户信息
        Users user=null;
        if(users!=null&&users.size()==1){
            //登入成功
            user=users.get(0);
        }else{
            //该手机号第一次登入，将用户信息存入数据库
            user=new Users();
            user.setAccount(phone); //手机
            user.setCreatdTime(new Date());
            user.setIsAuthentication(1);
            usersMapper.insertSelective(user);
        }
        //2.创建token(随机字符串)
        String token=UUID.randomUUID().toString();
        //3.将token和用户信息保存到Redis，以便后期使用
        ValueOperations<String,Users> vo=this.redisTemplate.opsForValue();
        vo.set("token:"+token,user,180,TimeUnit.MINUTES);
        return token;
    }


}
