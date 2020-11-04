package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收货地址
 * @author wangshilei
 * @date 2020/11/4 16:35
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Model<Address> {
    /**
     * 地址id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 收件人电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private Integer addressNum;

    /**
     * 是否是默认的
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Boolean delFlag;
}