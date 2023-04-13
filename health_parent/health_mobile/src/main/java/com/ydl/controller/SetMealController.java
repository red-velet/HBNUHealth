package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.Result;
import com.ydl.pojo.Setmeal;
import com.ydl.service.SetMealService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 20:11
 * @Introduction:
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    SetMealService setMealService;

    @GetMapping("getAllSetmeal")
    public Result getAllSetMeal() {
        try {
            List<Setmeal> list = setMealService.getAll();
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, list);
        } catch (Exception e) {
            //打印异常日志
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    @PostMapping("findById")
    public Result findById(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = setMealService.findByIdPro(id);
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, setmeal);
        } catch (Exception e) {
            //打印异常日志
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }
}
