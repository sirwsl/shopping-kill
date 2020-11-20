package com.wsl.shoppingKill;

import java.math.BigDecimal;

/**
 * @author : WangShiLei
 * @date : 2020/11/20 9:42 下午
 **/
public class test {
    public static void main(String[] args) {
       Double money = new Double("10.0");
       BigDecimal initPrice = new BigDecimal(money-tt(2.0)+"");

       System.out.println(initPrice);
    }

    public static Double tt(Double bd){
        if (bd == null){
            bd = 0.0;
        }
        return bd;
    }
}
