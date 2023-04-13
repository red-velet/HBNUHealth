package com.ydl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 15:27
 * @Introduction:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSettingVO implements Serializable {
    private static final long serialVersionUID = -1360846243988256692L;
    private Integer id;
    private Date orderDate;
    private Integer number;
    private Integer reservations;
    private Integer date;
}
