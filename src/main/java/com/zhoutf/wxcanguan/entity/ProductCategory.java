package com.zhoutf.wxcanguan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther zhoutf
 * @Date 2018/9/17 14:14
 * @Description
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class ProductCategory{

    @Id
    //1.0版本 (strategy = GenerationType.IDENTITY)可以不加，2.0版本必须加上不然报错
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    //@Temporal(TemporalType.DATE)
    private Date updateTime;


}
