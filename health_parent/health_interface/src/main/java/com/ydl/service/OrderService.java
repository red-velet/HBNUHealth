package com.ydl.service;

import com.ydl.pojo.OrderVO;

import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 16:48
 * @Introduction:
 */
public interface OrderService {
    /**
     * 用户进行预约体检套餐
     * * 1、用户所选日期未设置预约人数【预约失败】
     * * 2、用户所选日期当日预约人数已满【预约失败】
     * * 3、用户所需日期当日用户已经预约过了【预约失败】
     * * 4、用户是否未会员，是会员之间完成预约【预约成功】
     * * 5、不是会员，先注册为会员再进行预约【预约成功】
     *
     * @param map 用户输入的信息
     * @return 返回成功的套餐详细界面id
     */
    Integer order(Map<String, String> map) throws Exception;

    /**
     * 预约成功-信息回显
     *
     * @param id 预约订单id
     * @return 预约信息
     */
    OrderVO findById(Integer id);
}
