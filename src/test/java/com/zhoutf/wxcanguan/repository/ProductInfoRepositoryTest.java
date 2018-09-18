package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;


/**
 * @Auther zhoutf
 * @Date 2018/9/17 21:16
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findByProductId() {

        System.out.println("00000:"+repository.findById("100").get());
    }

    @Test
    public  void saveTest()
    {
        ProductInfo productInfo=new ProductInfo("123","皮蛋瘦肉粥",new BigDecimal(3.5),100,"very good","www.baidu.com",0,1);

        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> byProductStatus = repository.findByProductStatus(0);

        System.out.println("00000:"+byProductStatus);
    }
}