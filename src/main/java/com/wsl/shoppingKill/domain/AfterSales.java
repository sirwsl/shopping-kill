package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 售后记录表
 * @author wangshilei
 * @date 2020/11/4 16:39
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterSales extends Model<AfterSales> {
    /**
    * id
    */
    private Integer id;

    /**
    * 订单id
    */
    private String orderId;

    /**
    * 管理员id
    */
    private Integer adminId;

    /**
    * 处理内容
    */
    private String detail;

    /**
    * 处理类型 （0-退货退款 1-换货 3-仅退款）
    */
    private Integer type;

    /**
    * 处理时间
    */
    private LocalDateTime dealTime;

    /**
    * 是否解决（0-未解决 1-已解决  默认0）
    */
    private Boolean result;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}