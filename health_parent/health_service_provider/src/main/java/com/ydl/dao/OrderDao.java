package com.ydl.dao;

import com.ydl.pojo.Order;
import com.ydl.pojo.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 17:25
 * @Introduction:
 */
public interface OrderDao {

    List<Order> findByCondition(Order order);

    void add(Order order);

    OrderVO findById(@Param("id") Integer id);

    Map<String, String> findByIdMap(@Param("id") Integer id);
}
