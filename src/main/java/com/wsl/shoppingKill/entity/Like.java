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
public class Like implements Serializable {
    /**
    * 收藏id
    */
    private Long id;

    /**
    * 物品id
    */
    private Long goodsId;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}