package com.wsl.shoppingKill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author WangShilei
 * @date 2020/10/23-15:26
 **/
@Entity
@TableName("admin")
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Admin {
    /**主键id**/
    @Id
    @OrderBy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户id'")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer userId;

    @Column(length = 64,columnDefinition = "varchar(64) NOT NULL COMMENT '管理员名称' ")

    private String name;




    @Column(name="creat_time" ,columnDefinition = "NOT NULL COMMENT '创建时间'")
    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creatTime;

    @Column(name = "update_time",columnDefinition = "timestamp COMMENT '修改时间'", nullable = false, updatable = false, insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @TableField
    private LocalDateTime updateTime;

}
