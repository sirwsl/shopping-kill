package com.wsl.shoppingKill.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evaluate implements Serializable {
    /**
    * 评价id
    */
    private Long id;

    /**
    * 评价内容
    */
    private String text;

    /**
    * 商品id
    */
    private Long goodsId;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * 评价图片
    */
    private String img;

    /**
    * 评价几星
    */
    private Integer grade;

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