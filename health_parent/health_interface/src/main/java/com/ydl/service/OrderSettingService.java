package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.OrderSetting;
import com.ydl.pojo.OrderSettingVO;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 14:51
 * @Introduction:
 */
public interface OrderSettingService {
    void batchImportOrder(List<OrderSetting> list);

    /**
     * 查询该月内的所有预约设置
     *
     * @param date
     * @return
     */
    List<OrderSettingVO> findOrderSettingByDate(String date);

    /**
     * 编辑改日的预约人数
     *
     * @param orderSetting 日期和新的预约人数
     */
    void editNumberByDate(OrderSetting orderSetting);

    PageResult findOrdersPage(QueryPageBean queryPageBean);
}
