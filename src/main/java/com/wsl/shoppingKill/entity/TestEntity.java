package com.wsl.shoppingKill.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author WangShilei
 * @date 2020/10/21-12:28
 **/
@Entity
@TableName("test")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestEntity implements Serializable {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    @Column(name = "update_time",columnDefinition = "timestamp COMMENT '修改时间'", nullable = false, updatable = false, insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private boolean authority;

    String text;

    public TestEntity(Integer userId, String name, String text) {
        this.userId = userId;
        this.name = name;
        this.text = text;
    }
}
