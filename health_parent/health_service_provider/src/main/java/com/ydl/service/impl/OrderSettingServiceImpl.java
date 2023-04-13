package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.constant.Constants;
import com.ydl.dao.OrderSettingDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.OrderSetting;
import com.ydl.pojo.OrderSettingListVO;
import com.ydl.pojo.OrderSettingVO;
import com.ydl.service.OrderSettingService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 14:51
 * @Introduction:
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void batchImportOrder(List<OrderSetting> list) {
        for (OrderSetting orderSetting : list) {
            orderSettingDao.add(orderSetting);
        }
    }

    @Override
    public List<OrderSettingVO> findOrderSettingByDate(String date) {
        String[] split = date.split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]).append("-");
        String newDate = split[0];
        if (Integer.parseInt(split[1]) >= Constants.OCTOBER) {
            builder.append(split[1]);
        } else {
            builder.append("0").append(split[1]);
        }
        date = builder.toString();
        List<OrderSettingVO> list = orderSettingDao.findOrderSettingByDatePro(date);
        for (OrderSettingVO orderSettingVO : list) {
            Date orderDate = orderSettingVO.getOrderDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            String day = sdf.format(orderDate);
            orderSettingVO.setDate(Integer.parseInt(day));
        }
        return list;

        //date格式：2023-03
        //String begin = date + "-01";
        //String end = date + "-31";
        //Map<String, String> map = new ConcurrentHashMap<>();
        //map.put("begin", begin);
        //map.put("end", end);
        //List<OrderSettingVO> list = orderDao.findOrderSettingByDate(map);
        //for (OrderSettingVO orderSettingVO : list) {
        //    Date orderDate = orderSettingVO.getOrderDate();
        //    SimpleDateFormat sdf = new SimpleDateFormat("dd");
        //    String day = sdf.format(orderDate);
        //    orderSettingVO.setDate(Integer.parseInt(day));
        //}
        //return list;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        OrderSetting orderSetting1 = orderSettingDao.findByDate(orderSetting.getOrderDate());
        if (orderSetting1 != null) {
            //1、如果该日期已设置预约时间，则修改
            orderSettingDao.editNumberByDate(orderSetting);
        } else {
            //2、如果该日期没有设置预约时间，则添加
            orderSettingDao.add(orderSetting);
        }
    }

    @Override
    public PageResult findOrdersPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<OrderSettingListVO> list = orderSettingDao.findOrdersPage(queryPageBean.getQueryString());
        return new PageResult(list.getTotal(), list.getResult());
    }
}
