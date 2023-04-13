package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.constant.RedisConstant;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.entity.Result;
import com.ydl.pojo.Setmeal;
import com.ydl.service.SetMealService;
import com.ydl.utils.QiNiuCloudUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 19:26
 * @Introduction:
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    SetMealService setMealService;

    @Autowired
    JedisPool jedisPool;

    /**
     * 图片文件上传
     *
     * @return 上传结果
     */
    @PostMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
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
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @PostMapping("add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam("checkgroupIds") Integer[] checkGroupIds) {
        try {
            if (checkGroupIds == null) {
                checkGroupIds = new Integer[]{};
            }
            setMealService.add(setmeal, checkGroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            //logger.error
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @PostMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setMealService.findPage(queryPageBean);
    }

    @GetMapping("findById")
    public Result findById(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = setMealService.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }

    @GetMapping("findCheckGroupIdBySetMealId")
    public List<Integer> findCheckGroupIdBySetMealId(@RequestParam("id") Integer id) {
        try {
            List<Integer> list = setMealService.findCheckGroupIdBySetMealId(id);
            return list;
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            ArrayList<Integer> arrayList = new ArrayList<>();
            return arrayList;
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Setmeal setmeal, @RequestParam("checkgroupIds") Integer[] checkGroupIds) {
        try {
            if (checkGroupIds == null) {
                checkGroupIds = new Integer[]{};
            }
            setMealService.update(setmeal, checkGroupIds);
            return new Result(true, MessageConstant.UPDATE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            //logger.error
            e.printStackTrace();
            return new Result(false, MessageConstant.UPDATE_SETMEAL_FAIL);
        }
    }

    @GetMapping("delete")
    public Result delete(@RequestParam("id") Integer id) {
        try {
            setMealService.delete(id);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            //logger.error
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
