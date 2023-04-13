package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.constant.RedisConstant;
import com.ydl.constant.RedisMessageConstant;
import com.ydl.entity.Result;
import com.ydl.utils.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 16:15
 * @Introduction:
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    JedisPool jedisPool;

    @PostMapping("send4Order")
    public Result send4Order(@RequestParam String telephone) {
        //获取手机号进行验证
        //生成验证码
        String validateCode = ValidateCodeUtil.generateValidateCode4String(4);
        try {
            //发送验证码
            //SMSUtil.sendShortMessage(SMSUtil.VALIDATE_CODE, telephone, validateCode);
            System.out.println("预约短信验证码:> " + validateCode);
            //发送成功
            //1、将验证码存入redis
            Jedis jedis = jedisPool.getResource();
            String key = RedisMessageConstant.SEND_TYPE_ORDER + telephone;
            jedis.setex(key, RedisConstant.VALIDATE_TIME, validateCode);
            //2、告知前端发送成功
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            //发送失败
            e.printStackTrace();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @PostMapping("send4Login")
    public Result send4Login(@RequestParam String telephone) {
        //获取手机号进行验证
        //生成验证码
        String validateCode = ValidateCodeUtil.generateValidateCode4String(6);
        try {
            //发送验证码
            //SMSUtil.sendShortMessage(SMSUtil.VALIDATE_CODE, telephone, validateCode);
            System.out.println("登录短信信验证码:> " + validateCode);
            //发送成功
            //1、将验证码存入redis
            Jedis jedis = jedisPool.getResource();
            String key = RedisMessageConstant.SEND_TYPE_LOGIN + telephone;
            jedis.setex(key, RedisConstant.VALIDATE_TIME, validateCode);
            //2、告知前端发送成功
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            //发送失败
            e.printStackTrace();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
