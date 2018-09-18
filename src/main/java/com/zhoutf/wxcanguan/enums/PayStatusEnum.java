package com.zhoutf.wxcanguan.enums;

import lombok.Getter;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 12:48
 * @Description
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),SUCCESS(1,"支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
