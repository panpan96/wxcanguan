package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.OrderMaster;
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
 * @Date 2018/9/18 13:04
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private  OrderMasterRepository orderMasterRepository;
   
    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 5);

        orderMasterRepository.findByBuyerOpenid("110110", pageRequest);


    }

    @Test
    public  void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("兄");
        orderMaster.setBuyerPhone("12345789123");
        orderMaster.setBuyerAddress("幕课网");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster save = orderMasterRepository.save(orderMaster);
        System.out.println(save);
    }
}