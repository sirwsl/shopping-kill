package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商品表
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends Model<Goods> {
    /**
    * 商品id
    */
    private Integer id;

    /**
    * 商品名
    */
    private String name;

    /**
    * 类别id
    */
    private Integer typeId;

    /**
    * 图片地址
    */
    private String imgUrl;

    /**
    * 是否上架
    */
    private Boolean shelf;

    /**
    * 商品描述
    */
    private String detail;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 逻辑删除
    */
    private Boolean delFlag;
}