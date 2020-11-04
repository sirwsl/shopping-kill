package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 限时抢购活动表
 * @author wangshilei
 * @date 2020/11/4 16:29
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends Model<Activity> {
    /**
    * 秒杀活动
    */
    private Integer id;

    /**
    * 商品id
    */
    private Integer goodsId;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    private LocalDateTime endTime;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
    private LocalDateTime updateTime;

    /**
    * 逻辑删除
    */
    private Boolean delFlag;
}