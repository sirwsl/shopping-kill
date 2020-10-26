package com.wsl.shoppingKill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wsl
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * 购买商品id
    */
    private Long goodsInfo;

    /**
    * 数量
    */
    private Integer goodsNum;

    /**
    * 状态 0未失效 1失效
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime created;

    /**
    * 更新时间
    */
    private LocalDateTime updated;

    private static final long serialVersionUID = 1L;
}