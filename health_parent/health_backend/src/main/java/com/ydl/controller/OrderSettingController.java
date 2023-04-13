package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.entity.Result;
import com.ydl.pojo.OrderSetting;
import com.ydl.pojo.OrderSettingVO;
import com.ydl.service.OrderSettingService;
import com.ydl.utils.PoiUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 14:38
 * @Introduction:
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;

    @PostMapping("upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            List<String[]> list = PoiUtil.readExcel(excelFile);
            ArrayList<OrderSetting> orders = new ArrayList<>();
            for (String[] string : list) {
                Date date = new Date(string[0]);
                int number = Integer.parseInt(string[1]);
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(date);
                orderSetting.setNumber(number);
                orders.add(orderSetting);
            }
            orderSettingService.batchImportOrder(orders);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            //打印异常日志
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @PostMapping("getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam("date") String date) {
        try {
            List<OrderSettingVO> list = orderSettingService.findOrderSettingByDate(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            //打印异常日志
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @PostMapping("editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            //打印异常日志
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @PostMapping("findOrdersPage")
    public PageResult findOrdersPage(@RequestBody QueryPageBean queryPageBean) {
        return orderSettingService.findOrdersPage(queryPageBean);
    }
}
