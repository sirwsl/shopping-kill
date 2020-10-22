package com.wsl.shoppingKill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author WangShilei
 * @date 2020/10/21-12:28
 **/
@Data
@TableName("test")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField
    private String name;

    @TableField
    private Integer age;
}
