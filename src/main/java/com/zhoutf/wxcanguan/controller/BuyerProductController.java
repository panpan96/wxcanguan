package com.zhoutf.wxcanguan.controller;

import com.zhoutf.wxcanguan.VO.ProductInfoVO;
import com.zhoutf.wxcanguan.VO.ProductVO;
import com.zhoutf.wxcanguan.VO.ResultVO;
import com.zhoutf.wxcanguan.entity.ProductCategory;
import com.zhoutf.wxcanguan.entity.ProductInfo;
import com.zhoutf.wxcanguan.service.ProductCategoryService;
import com.zhoutf.wxcanguan.service.ProductInfoService;
import com.zhoutf.wxcanguan.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 9:44
 * @Description
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {

        //查询所有上架商品
        List<ProductInfo> upAll = productInfoService.findUpAll();

        //查询类目
        List<Integer> categoryTypeList=upAll.stream().map(e->e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //数据拼装
        List<ProductVO> productVOList=new ArrayList<>();

        for (ProductCategory productCategory:productCategoryList
             ) {
            ProductVO productVO = new ProductVO();

            productVO.setCategoryName(productCategory.getCategoryName());

            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList=new ArrayList<>();

            for (ProductInfo productInfo:upAll
                 ) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {

                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
