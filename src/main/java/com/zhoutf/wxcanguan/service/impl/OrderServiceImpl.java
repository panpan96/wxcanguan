package com.zhoutf.wxcanguan.service.impl;

import com.zhoutf.wxcanguan.converter.OrderMaster2OrderDto;
import com.zhoutf.wxcanguan.dto.CartDto;
import com.zhoutf.wxcanguan.dto.OrderDto;
import com.zhoutf.wxcanguan.entity.OrderDetail;
import com.zhoutf.wxcanguan.entity.OrderMaster;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import com.zhoutf.wxcanguan.enums.OrderStatusEnum;
import com.zhoutf.wxcanguan.enums.PayStatusEnum;
import com.zhoutf.wxcanguan.enums.ResultEnum;
import com.zhoutf.wxcanguan.exception.SellException;
import com.zhoutf.wxcanguan.repository.OrderDetailRepository;
import com.zhoutf.wxcanguan.repository.OrderMasterRepository;
import com.zhoutf.wxcanguan.service.OrderService;
import com.zhoutf.wxcanguan.service.ProductInfoService;
import com.zhoutf.wxcanguan.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 13:43
 * @Description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //List<CartDto> cartDtoList = new ArrayList<>();

        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()
                ) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
            orderAmount = (new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice())).add(orderAmount);

            //2.订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
//            CartDto cartDto = new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDtoList.add(cartDto);
        }
        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);


        //4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {


        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        //判断订单状态
      //  OrderMaster orderMaster = orderMasterRepository.findById(orderDto.getOrderId()).get();
        if(!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())))
        {
            log.error("[取消订单出错，订单非新订单,OrderId={},OrderStatus={},",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterRepository.save(orderMaster);

        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);

        //如果已支付，需要返款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            log.error("已支付，需要返款");
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {

        //判断订单状态
        if(!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))) {
            log.error("[取消订单出错，订单非新订单,OrderId={},OrderStatus={},",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
          }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);

        if(master==null) {
            log.error("[完结订单出错]");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))) {
            log.error("[支付订单出错，订单非新订单,OrderId={},OrderStatus={},",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            log.error("[订单已支付，不可重复支付]");
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);

        if(master==null) {
            log.error("[完结订单出错]");
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        return orderDto;
    }
}
