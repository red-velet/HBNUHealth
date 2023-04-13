package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.constant.RedisMessageConstant;
import com.ydl.entity.Result;
import com.ydl.pojo.OrderVO;
import com.ydl.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 16:37
 * @Introduction:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    JedisPool jedisPool;

    @Reference
    OrderService orderService;

    @PostMapping("submit")
    public Result send4Order(@RequestBody Map<String, String> map) {
        //1、校验redis内的验证码和前端发送过来的验证码
        Jedis jedis = jedisPool.getResource();
        String telephone = map.get("telephone");
        String key = RedisMessageConstant.SEND_TYPE_ORDER + telephone;
        String redisValidateCode = jedis.get(key);
        String validateCode = map.get("validateCode");
        if (redisValidateCode == null || !redisValidateCode.equals(validateCode)) {
            //2、验证码不一致：预约失败
            return new Result(false, MessageConstant.ORDER_FAIL);
        }

        //3、验证码一致：进行预约设置
        map.put("orderType", "微信预约");
        Integer id = null;
        try {
            id = orderService.order(map);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        if (id == null) {
            //4、用户当日已经预约、预约人数已满等原因->预约失败
            return new Result(false, MessageConstant.ORDER_FAIL);
        }

        //4、预约成功，发送短信通知
        try {
            //SMSUtil.sendShortMessage(SMSUtil.ORDER_NOTICE, telephone, "wfw");
            System.out.println("发送短信成功");
            //5、发送短信通知成功
        } catch (Exception e) {
            //6、发送短信通知失败-重新发送
            e.printStackTrace();
        }
        return new Result(true, MessageConstant.ORDER_SUCCESS, id);
    }

    @PostMapping("findById")
    public Result findById(@RequestParam("id") Integer id) {
        try {
            OrderVO orderVO = orderService.findById(id);
            return new Result(true, MessageConstant.ORDER_ECHO_SUCCESS, orderVO);
        } catch (Exception e) {
            //打印错误日志
            return new Result(false, MessageConstant.ORDER_ECHO_FAIL);
        }
    }
}
