package com.zhoutf.wxcanguan.exception;

import com.zhoutf.wxcanguan.enums.ResultEnum;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:54
 * @Description
 */
public class SellException extends  RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

}
