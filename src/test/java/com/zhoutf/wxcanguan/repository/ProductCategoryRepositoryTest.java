package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 14:24
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private  ProductCategoryRepository productCategoryRepository;

    @Test
    public  void findOneTest(){

        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println("ddddd:"+productCategory.toString());
    }

    @Test
    @Transactional
    public  void saveTest(){
      //  ProductCategory productCategory = productCategoryRepository.findById(1).get();
        ProductCategory productCategory = new ProductCategory();
        //productCategory.setCategoryId(1);
        productCategory.setCategoryName("爱");
        productCategory.setCategoryType(6);

        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest()
    {
        List<Integer> list= Arrays.asList(1,3,5);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,byCategoryTypeIn.size());
        System.out.println(byCategoryTypeIn);
    }
}