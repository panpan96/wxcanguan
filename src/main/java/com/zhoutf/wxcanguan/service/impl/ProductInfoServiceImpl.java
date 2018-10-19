package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.dto.CartDto;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import com.zhoutf.wxcanguan.enums.ProductStatusEnum;
import com.zhoutf.wxcanguan.enums.ResultEnum;
import com.zhoutf.wxcanguan.exception.SellException;
import com.zhoutf.wxcanguan.repository.ProductInfoRepository;
import com.zhoutf.wxcanguan.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {

        for (CartDto cartDto:cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();

            if (productInfo == null) {

                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {

        for (CartDto cartDto:cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();
            if (productInfo == null) {

                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();

            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }

    }
}
