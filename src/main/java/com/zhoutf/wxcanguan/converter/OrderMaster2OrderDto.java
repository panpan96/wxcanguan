package com.zhoutf.wxcanguan.converter;

import com.zhoutf.wxcanguan.dto.OrderDto;
import com.zhoutf.wxcanguan.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMaster2OrderDto {

    public static OrderDto convert(OrderMaster orderMaster) {

        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return  orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {

        List<OrderDto> orderDtoList= orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
        return  orderDtoList;
    }

}
