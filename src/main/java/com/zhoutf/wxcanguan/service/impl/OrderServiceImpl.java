package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.dto.OrderDto;
import com.zhoutf.wxcanguan.entity.OrderDetail;
import com.zhoutf.wxcanguan.entity.OrderMaster;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import com.zhoutf.wxcanguan.enums.ResultEnum;
import com.zhoutf.wxcanguan.exception.SellException;
import com.zhoutf.wxcanguan.repository.OrderDetailRepository;
import com.zhoutf.wxcanguan.repository.OrderMasterRepository;
import com.zhoutf.wxcanguan.service.OrderService;
import com.zhoutf.wxcanguan.service.ProductInfoService;
import com.zhoutf.wxcanguan.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:43
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail:orderDto.getOrderDetailList()
             ) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
            orderAmount=(new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice())).add(orderAmount);

        //2.订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);


        return null;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
