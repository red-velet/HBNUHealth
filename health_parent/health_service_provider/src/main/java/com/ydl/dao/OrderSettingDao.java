package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.OrderSetting;
import com.ydl.pojo.OrderSettingListVO;
import com.ydl.pojo.OrderSettingVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 15:01
 * @Introduction:
 */
public interface OrderSettingDao {

    void add(OrderSetting orderSetting);

    List<OrderSettingVO> findOrderSettingByDate(Map<String, String> map);

    void editNumberByDate(OrderSetting orderSetting);

    OrderSetting findByDate(@Param("date") Date orderDate);

    List<OrderSettingVO> findOrderSettingByDatePro(@Param("date") String date);

    OrderSetting findByOrderDate(@Param("orderDate") String orderDate);


    void editReservationsByOrderDate(OrderSetting orderSetting);

    Page<OrderSettingListVO> findOrdersPage(@Param("queryString") String queryString);
}
