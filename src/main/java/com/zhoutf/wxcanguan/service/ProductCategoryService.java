package com.zhoutf.wxcanguan.service;

import com.zhoutf.wxcanguan.entity.ProductCategory;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 16:38
 * @Description
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
