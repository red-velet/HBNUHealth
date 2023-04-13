package com.ydl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 17:31
 * @Introduction:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSetting implements Serializable {
    private static final long serialVersionUID = 1254302190190222050L;
    private Integer id;
    private Date orderDate;
    private Integer number;
    private Integer reservations;
}
