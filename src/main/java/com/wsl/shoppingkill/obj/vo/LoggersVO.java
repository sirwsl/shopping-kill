package com.wsl.shoppingkill.obj.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wsl.shoppingkill.common.fastjson.BaseEnumSerializer;
import com.wsl.shoppingkill.common.fastjson.IEnumDeSerializer;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志记录表
 *
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class LoggersVO  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日志id
     */
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
     * 操作人姓名
     **/
    private String name;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

  
}
