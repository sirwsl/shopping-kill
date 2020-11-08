package com.wsl.shoppingKill.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wsl.shoppingKill.common.fastjson.BaseEnumSerializer;
import com.wsl.shoppingKill.common.fastjson.IEnumDeSerializer;
import com.wsl.shoppingKill.constant.SexEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
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
    private String name;


    /**
    * 密码
    */
    private String password;


    /**
     * 性别
     */
    @JSONField(serializeUsing = BaseEnumSerializer.class)
    @JsonDeserialize(using = IEnumDeSerializer.class)
    private SexEnum sex;
    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 家庭住址
    */
    private String address;

    /**
    * 微信
    */
    private String weChat;

    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
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