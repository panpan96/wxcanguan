package com.zhoutf.wxcanguan.entity;

import com.zhoutf.wxcanguan.enums.OrderStatusEnum;
import com.zhoutf.wxcanguan.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 12:41
 * @Description
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus=OrderStatusEnum.NEW.getCode();

    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
