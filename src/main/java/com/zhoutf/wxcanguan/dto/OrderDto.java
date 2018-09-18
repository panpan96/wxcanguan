package com.zhoutf.wxcanguan.dto;

import com.zhoutf.wxcanguan.entity.OrderDetail;
import com.zhoutf.wxcanguan.enums.OrderStatusEnum;
import com.zhoutf.wxcanguan.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:36
 * @Description
 */
@Data
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
