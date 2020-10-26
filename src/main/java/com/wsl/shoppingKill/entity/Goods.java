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
public class Goods implements Serializable {
    /**
    * 商品id，商品编号
    */
    private Long id;

    /**
    * 商品标题
    */
    private String title;

    /**
    * 商品描述
    */
    private String describe;

    /**
    * 商品图片
    */
    private String image;

    /**
    * 所属分类
    */
    private Long cid;

    /**
    * 商品状态 1正常 0下架
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}