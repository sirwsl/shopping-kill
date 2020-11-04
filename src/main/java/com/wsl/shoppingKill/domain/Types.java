package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 商品类别表
 * @author wangshilei
 * @date 2020/11/4 16:45
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Types extends Model<Types> {
    /**
    * 类别id
    */
    private Integer id;

    /**
    * 类别名字
    */
    private String name;

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