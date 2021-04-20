package com.wsl.shoppingkill.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.wsl.shoppingkill.common.fastjson.BaseEnumSerializer;
import com.wsl.shoppingkill.common.fastjson.IEnumDeSerializer;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志记录表
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/
@TableName("t_loggers")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Loggers extends Model<Loggers> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 日志id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 操作内容
    */
    private String detail;

    /**
    * 操作人id
    */
    private Long manId;

    /**
    * 操作类型(0-用户 1-管理员)
    */
    private Integer type;

    /**
    * 等级(0-正常 1-良好 2-严重)
    */

    @JSONField(serializeUsing = BaseEnumSerializer.class)
    @JsonDeserialize(using = IEnumDeSerializer.class)
    private LoggerEnum grade;

    /**
     * ip地址
     **/
    private String ip;

    /**
    * 创建时间(操作时间)
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;



    public static final String ID = "id";

    public static final String DETAIL = "detail";

    public static final String MAN_ID = "man_id";

    public static final String TYPE = "type";

    public static final String GRADE = "grade";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";

}