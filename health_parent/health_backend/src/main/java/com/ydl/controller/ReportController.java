package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.Result;
import com.ydl.service.ReportService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 18:43
 * @Introduction:
 */
@RestController
@RequestMapping("report")
public class ReportController {
    @Reference
    ReportService reportService;

    @GetMapping("getMemberReport")
    public Result getMemberReport() {
        //1、获取过去到现在一年间的会员数量
        List<String> months = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);
        //获取月份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1);
            Date date = calendar.getTime();
            months.add(sdf.format(date));
        }
        //获取数量
        List<Integer> members = reportService.findMemberCountByMonth(months);

        //2、添加到map内返回
        Map<String, Object> map = new HashMap<>();
        map.put("months", months);
        map.put("members", members);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
    }

    @GetMapping("getSetmealReport")
    public Result getSetmealReport() {
        Map<String, Object> map = new HashMap<>();
        List<String> setmealNames = new ArrayList<>();

        List<Map<String, Object>> setmealCount = reportService.findSetMealCount();
        for (Map<String, Object> stringObjectMap : setmealCount) {
            String name = (String) stringObjectMap.get("name");
            setmealNames.add(name);
        }

        map.put("setmealNames", setmealNames);
        map.put("setmealCount", setmealCount);
        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, map);
    }

    @GetMapping("getBusinessReportData")
    public Result getBusinessReportData() {
        try {
            Map<String, Object> map = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (Exception e) {
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
