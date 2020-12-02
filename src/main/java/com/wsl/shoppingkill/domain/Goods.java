package com.wsl.shoppingkill.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品表
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/
@TableName("t_goods")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Goods extends Model<Goods> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商品id
    */
    @Id
    @TableId(type = IdType.AUTO)

    private Long id;

    /**
    * 商品名
    */
    @NotNull(message = "商品名称不能为空")
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
    * 类别id
    */
    private Long typeId;

    /**
    * 图片地址
    */
    private String imgUrl;

    /**
     * 图片
     */
    @TableField(exist = false)
    private transient MultipartFile file;

    /**
    * 是否上架
    */
    @NotNull(message = "请选择是否上架")
    @NotBlank(message = "请选择是否上架")
    private Boolean shelf;

    /**
    * 商品描述
    */
    @NotNull(message = "商品描述不能为空")
    @NotBlank(message = "商品描述不能为空")
    private String detail;

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
    * 更新时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 逻辑删除
    */
    @TableLogic
    private Boolean delFlag;



    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TYPE_ID = "type_id";

    public static final String IMG_URL = "img_url";

    public static final String SHELF = "shelf";

    public static final String DETAIL = "detail";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";

}