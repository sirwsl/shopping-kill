package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * SKU在秒杀时候限制上限数量
 * @author wangshilei
 * @date 2020/11/4 16:30
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLimit extends Model<ActivityLimit> {
    /**
    * id
    */
    private Integer id;

    /**
    * skuId
    */
    private Integer skuId;

    /**
    * 活动id
    */
    private Integer activityId;

    /**
    * 上限数量
    */
    private Integer limitNum;

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