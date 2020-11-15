package com.wsl.shoppingKill.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.wsl.shoppingKill.common.fastjson.BaseEnumSerializer;
import com.wsl.shoppingKill.common.fastjson.IEnumDeSerializer;
import com.wsl.shoppingKill.constant.SexEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * 管理员表
 * @author wangshilei
 * @date 2020/11/4 16:31
 **/
@TableName("t_admin")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Admin extends Model<Admin> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @NotNull(message="用户名不能为空")
    private String name;


    /**
    * 密码
    */
    @NotNull(message = "密码不能为空")
    private String password;


    /**
     * 性别
     */
    @JSONField(serializeUsing = BaseEnumSerializer.class)
    @JsonDeserialize(using = IEnumDeSerializer.class)
    @NotNull(message = "性别不能为空")
    private SexEnum sex;
    /**
    * 身份证号
    */
    @NotNull(message = "身份证号不能为空")
    private String idCard;

    /**
    * 手机号
    */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
    * 家庭住址
    */
    @NotNull(message = "家庭住址不能为空")
    private String address;

    /**
    * 微信
    */
    private String weChat;

    /**
    * 创建时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否离职
    */
    @TableLogic
    private Boolean delFlag;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String SEX = "sex";

    public static final String ID_CARD = "id_card";

    public static final String PHONE = "phone";

    public static final String ADDRESS = "address";

    public static final String WE_CHAT = "we_chat";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";
}