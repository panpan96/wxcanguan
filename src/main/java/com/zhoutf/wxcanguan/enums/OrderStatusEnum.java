package com.zhoutf.wxcanguan.enums;

import lombok.Getter;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 12:44
 * @Description 订单状态
 */
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),FINISHED(1,"完结"),CANCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
