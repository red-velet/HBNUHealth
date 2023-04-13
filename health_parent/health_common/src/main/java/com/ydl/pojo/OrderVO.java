package com.ydl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 20:35
 * @Introduction: 预约成功信息回显
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 2743674317927109940L;
    /**
     * 体检人、体检套餐、体检日期、预约类型
     */
    private String member;
    private String setmeal;
    private Date orderDate;
    private String orderType;
}
