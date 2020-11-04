package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 购物车
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Model<Cart> {
    /**
    * 购物车id
    */
    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * sku_id
    */
    private Integer skuId;

    /**
    * 创建时间（加入时间）
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 状态(0-正常 1-已失效)
    */
    private Integer status;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}