package com.ydl.jobs;

import com.ydl.constant.RedisConstant;
import com.ydl.utils.QiNiuCloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 12:45
 * @Introduction:
 */
public class ClearImgJob {

    @Autowired
    JedisPool jedisPool;

    /**
     * 定时清除redis内的垃圾图片
     */
    public void clearImg() {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String fileName : set) {
            //七牛云删除垃圾图片
            QiNiuCloudUtil.deleteFileFrom2QiNiu(fileName);
            //redis删除垃圾图片
            //jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            System.out.println("删除七牛云图片.....");

        }
        jedis.del(RedisConstant.SETMEAL_PIC_RESOURCES);
        jedis.del(RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        System.out.println("删除redis图片名.....");
    }
}
