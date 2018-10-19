package com.zhoutf.wxcanguan.utils;

import java.util.Random;

/**
 * @Auther zhoutf
 * @Date 2018/9/18 14:20
 * @Description
 */
public class KeyUtil {

    /**
      * @Description  生成唯一键（时间+随机数）
      * @Param []
      * @return java.lang.String
      * @Author zhoutf
      * @Date 2018/9/18 14:21
      */
    public static synchronized String genUniqueKey()
    {
        Random random = new Random();
        Integer num=random.nextInt(900000)+100000;
       // System.out.println("num"+num);
       // System.out.println("System.console()"+System.currentTimeMillis());
        return System.currentTimeMillis()+String.valueOf(num);
    }

    public static void main(String[] args) {


        for(int i=0;i<10;i++) {
            System.out.println(genUniqueKey());
        }
    }
}
