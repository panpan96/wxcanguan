package com.zhoutf.wxcanguan.enums;

import lombok.Getter;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:56
 * @Description
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不存在"),

    ORDER_DETAIL_EMPTY(15,"订单不存在商品"),

    ORDER_UPDATE_FAIL(16,"完结订单出错"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
