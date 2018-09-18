package com.zhoutf.wxcanguan.repository;

import com.zhoutf.wxcanguan.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 12:57
 * @Description
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
