package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 14:23
 * @Description
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
       List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
