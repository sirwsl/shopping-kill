package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 评价表
 * @author wangshilei
 * @date 2020/11/4 16:40
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appraisal extends Model<Appraisal> {
    /**
    * id
    */
    private Integer id;

    /**
    * 订单id
    */
    private String orderId;

    /**
    * 评价内容
    */
    private String detail;

    /**
    * 评价图片
    */
    private String imgUrl;

    /**
    * 评价星级（1-5）
    */
    private Integer grade;

    /**
    * 创建时间（评价时间）
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