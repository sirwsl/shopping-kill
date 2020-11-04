package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 广告内容
 * @author wangshilei
 * @date 2020/11/4 16:36
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertise extends Model<Advertise> {
    /**
    * 广告id
    */
    private Integer id;

    /**
    * 广告图片链接
    */
    private String imgUrl;

    /**
    * 目标url
    */
    private String targetUrl;

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
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}