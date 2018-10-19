package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.dto.OrderDto;
import com.zhoutf.wxcanguan.entity.OrderDetail;
import com.zhoutf.wxcanguan.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther zhoutf
 * @Date 2018/10/10 15:45
 * @Description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;


    @Test
    //@Transactional
    public void create() {

        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName("周腾飞");
        orderDto.setBuyerPhone("17855822366");
        orderDto.setBuyerAddress("henanshengshang");
        orderDto.setBuyerOpenid("110111");

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setProductId("101");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDe = new OrderDetail();

        orderDe.setProductId("123");
        orderDe.setProductQuantity(2);
        orderDetailList.add(orderDe);

        orderDto.setOrderDetailList(orderDetailList);

        orderService.create(orderDto);
    }
    @Test
    //@Transactional
    public void findOne() {
       // OrderDto orderDto= orderService.findOne("1539928094502871119");
//
//        PageRequest pageable = PageRequest.of(0, 2);
//
//        Page<OrderDto> orderDtoPage= orderService.findList("110111",pageable);
//
//     List<OrderDto>  list= orderDtoPage.getContent();
//        System.out.println(list);
//        System.out.println(orderDtoPage);



        OrderDto orderDto= orderService.findOne("1539928094502871119");
        OrderDto dto = orderService.paid(orderDto);
        System.out.println(dto);



    }

}