package com.zhoutf.wxcanguan.service;

import com.zhoutf.wxcanguan.dto.CartDto;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 21:23
 * @Description
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
      * @Description 查询所有在架商品
      * @Params:
      * @return:
      * @Auther zhoutf
      * @Date: 2018/9/17 21:33
      */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDto> cartDtoList);

    //减库存
    void decreaseStock(List<CartDto> cartDtoList);
}
