package com.wsl.shoppingKill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author WangShilei
 * @date 2020/10/21-12:28
 **/
@Data
@TableName("test")
public class TestEntity {
    @TableId(value = "Id",type = IdType.AUTO)
    private Integer id;
}
