package com.zhoutf.wxcanguan.VO;

import lombok.Data;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 9:50
 * @Description 返回信息格式
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
