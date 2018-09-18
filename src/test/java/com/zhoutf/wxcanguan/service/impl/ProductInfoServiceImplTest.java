package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 22:07
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {


    @Autowired
    private ProductInfoServiceImpl productInfoService;


    @Test
    public void findOne() {

        System.out.println(productInfoService.findOne("123"));
    }

    @Test
    public void findUpAll() {
        System.out.println("Size:::"+productInfoService.findUpAll().size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=PageRequest.of(0,5);
        Page<ProductInfo> all = productInfoService.findAll(pageRequest);

        System.out.println("allll:"+all.getTotalElements());
    }

    @Test
    public void save() {

        ProductInfo productInfo=new ProductInfo("101","瘦肉粥",new BigDecimal(8.5),100,"very good","www.baidu.com",0,1);
        productInfoService.save(productInfo);

    }
}