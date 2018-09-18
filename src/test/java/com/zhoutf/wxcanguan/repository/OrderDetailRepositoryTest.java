package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:19
 * @Description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {


    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrOrderId() {
    }

    @Test
    public void save() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("110110");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("粥");
        orderDetail.setProductPrice(new BigDecimal(3.5));
        orderDetail.setProductQuantity(10);

        OrderDetail result = repository.save(orderDetail);

    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = repository.findByOrderId("110110");
        System.out.println(orderDetailList);
    }
}