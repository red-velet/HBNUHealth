package com.ydl.controller;

import com.alibaba.fastjson.JSON;
import com.ydl.constant.Constants;
import com.ydl.constant.MessageConstant;
import com.ydl.constant.RedisMessageConstant;
import com.ydl.entity.Result;
import com.ydl.pojo.Member;
import com.ydl.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 10:50
 * @Introduction:
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    JedisPool jedisPool;

    @Reference
    MemberService memberService;

    @RequestMapping("login")
    public Result login(@RequestBody Map<String, String> map, HttpServletResponse response) {
        //1、校验用户输入的验证码和redis内的验证码是否一致
        String validateCode = map.get("validateCode");
        String telephone = map.get("telephone");
        Jedis jedis = jedisPool.getResource();
        String redisValidateCode = jedis.get(RedisMessageConstant.SEND_TYPE_LOGIN + telephone);
        if (redisValidateCode == null || !redisValidateCode.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        Member member = memberService.findByTelephone(telephone);
        //1、检查用户是否为会员
        //不是会员则注册为会员
        if (member == null) {
            member = new Member();
            member.setRegTime(new Date());
            member.setPhoneNumber(telephone);
            member.setPassword(Constants.DEFAULT_PASSWORD);
            memberService.add(member);
        }

        //登录成功，添加Cookie到客户端
        //1、保存会员信息到token
        Cookie cookie = new Cookie("login_member_telephone", telephone);
        cookie.setPath("/");
        cookie.setMaxAge(Constants.COOKIE_TIME);
        response.addCookie(cookie);
        //2、保存登录信息到redis
        String value = JSON.toJSONString(member);
        String key = RedisMessageConstant.LOGGED_IN + telephone;
        jedis.setex(key, Constants.LOGIN_INFO_TIME, value);

        return new Result(true, MessageConstant.LOGIN_SUCCESS);
    }
}
