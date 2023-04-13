package com.ydl.service.impl;

import com.ydl.dao.MemberDao;
import com.ydl.dao.OrderDao;
import com.ydl.dao.OrderSettingDao;
import com.ydl.pojo.Member;
import com.ydl.pojo.Order;
import com.ydl.pojo.OrderSetting;
import com.ydl.pojo.OrderVO;
import com.ydl.service.OrderService;
import com.ydl.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 16:59
 * @Introduction:
 */
@Service(interfaceClass = OrderService.class)
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    MemberDao memberDao;


    @Override
    public Integer order(Map<String, String> map) throws Exception {
        System.out.println("order impl---------");
        //进行预约设置
        //1、用户所选日期未设置预约人数【预约失败】
        String orderDate = map.get("orderDate");
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(orderDate);
        System.out.println("orderSetting = " + orderSetting);
        if (orderSetting == null) {
            return null;
        }

        //2、用户所选日期当日预约人数已满【预约失败】
        int reservations = orderSetting.getReservations() == null ? 0 : orderSetting.getReservations();
        int number = orderSetting.getNumber();
        System.out.println("reservations = " + reservations);
        System.out.println("number = " + number);
        if (reservations >= number) {
            return null;
        }
        //3、用户所需日期当日用户已经预约过了【预约失败】
        //通过用户的手机号查询用户的member_id
        String telephone = map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        System.out.println("member = " + member);
        //用户存在,则检查是否已预约
        if (member != null) {
            //通过member_id查询是否已经预约
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(DateUtil.parseString2Date(orderDate));
            String setmealId = map.get("setmealId");
            int id = Integer.parseInt(setmealId);
            order.setSetmealId(id);
            //order.setSetmealId(Integer.parseInt(setmealId));
            List<Order> list = orderDao.findByCondition(order);
            if (list == null || list.size() > 0) {
                return null;
            }
        } else {
            //用户不存在则注册再预约
            member = new Member();
            member.setName(map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard(map.get("idCard"));
            member.setSex(map.get("sex"));
            member.setRegTime(new Date());

            //自动完成会员注册
            memberDao.add(member);
            System.out.println("注册成功");
        }
        //预约成功
        //1、添加一条预约记录
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(DateUtil.parseString2Date(orderDate));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt(map.get("setmealId")));
        order.setOrderType(map.get("orderType"));
        orderDao.add(order);
        System.out.println("预约成功");
        //2、更新预约设置表预约人数+1
        orderSetting.setReservations((orderSetting.getReservations() == null ? 0 : orderSetting.getReservations()) + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        return order.getId();
    }

    @Override
    public OrderVO findById(Integer id) {
        try {
            Map<String, String> byIdMap = orderDao.findByIdMap(id);
            System.out.println("byIdMap.toString() = " + byIdMap.toString());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return orderDao.findById(id);
    }
}
