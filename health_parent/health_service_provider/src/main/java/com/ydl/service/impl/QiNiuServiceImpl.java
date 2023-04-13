package com.ydl.service.impl;

import com.ydl.constant.RedisConstant;
import com.ydl.service.QiNiuService;
import com.ydl.utils.QiNiuCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 9:11
 * @Introduction:
 */
@Service(interfaceClass = QiNiuService.class)
@Slf4j
public class QiNiuServiceImpl implements QiNiuService {
    @Autowired
    JedisPool jedisPool;


    @Override
    public String uploadImage(MultipartFile imgFile) throws IOException {
        //获取图片原始名称
        String originalFilename = imgFile.getOriginalFilename();

        //获取图片后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //设置新的无重复文件名
        String fileName = UUID.randomUUID().toString() + suffix;

        //将图片上传到七牛云
        QiNiuCloudUtil.upload2QiNiu(imgFile.getBytes(), fileName);

        //将文件名存储到redis中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);

        return fileName;
    }
}
