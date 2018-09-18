package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.entity.ProductInfo;
import com.zhoutf.wxcanguan.enums.ProductStatusEnum;
import com.zhoutf.wxcanguan.repository.ProductInfoRepository;
import com.zhoutf.wxcanguan.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 21:32
 * @Description
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {


    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
