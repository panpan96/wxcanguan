package com.zhoutf.wxcanguan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther zhoutf
 * @Date 2018/10/10 15:10
 * @Description  购物车对象
 */

@Data
@AllArgsConstructor
public class CartDto {

    private String productId;

    private Integer productQuantity;

}
