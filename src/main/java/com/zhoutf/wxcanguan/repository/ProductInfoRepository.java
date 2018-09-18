package com.zhoutf.wxcanguan.repository;

import com.sun.imageio.plugins.common.I18N;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 21:13
 * @Description
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}

