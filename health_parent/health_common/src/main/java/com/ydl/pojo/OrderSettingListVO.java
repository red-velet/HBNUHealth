package com.ydl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 16:36
 * @Introduction: 显示预约列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSettingListVO implements Serializable {
    private static final long serialVersionUID = 7994722868833504673L;
    private Integer id;
    private Date orderDate;
    private String orderAddress;
    private String name;
    private String phoneNumber;
    private String orderType;
    private String orderStatus;
}
