package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 16:47
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired

    ProductCategoryServiceImpl productCategoryService;



    @Test
    public void findOne() {
        System.out.println(productCategoryService.findOne(1));

    }

    @Test
    public void findAll() {
        System.out.println(productCategoryService.findAll());
    }

    @Test
    public void findByCategoryTypeIn() {

        System.out.println(productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("aa");

        productCategory.setCategoryType(265);

        productCategoryService.save(productCategory);

    }
}