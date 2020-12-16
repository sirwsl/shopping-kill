package com.wsl.shoppingkill.obj.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SkuVO
 * @author WangShilei
 * @date 2020/11/17-16:54
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SkuVOs implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 商品id
     */
    @NotNull(message = "goodsId不能为空")
    private Long goodsIds;


    /**
     * 商品属性
     */
    @NotNull(message = "商品属性不能为空")
    @NotBlank(message = "商品属性不能为空")
    private String attributes;

    /**
     * 商品图片地址
     */
    private String imgUrls;

    /**
     * 商品图片
     */
    @TableField(exist = false)
    private transient MultipartFile imgs;
    /**
     * 进价
     */
    @NotNull(message = "进价不能为空")
    @Min(value = 0,message = "进价不能为服输")
    private BigDecimal realPrices;

    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空")
    @Min(value = 0,message = "成本价不能为服输")
    private BigDecimal costPrices;

    /**
     * 售价
     */
    @NotNull(message = "售价不能为空")
    @Min(value = 0,message = "售价不能为服输")
    private BigDecimal sellPrices;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    @Min(value = 0,message = "数量不能小于0")
    private Integer nums;

    /**
     * 预警量（默认10）
     */
    @NotNull(message = "预警量不能为空")
    private Integer warnNums;

    /**
     * 快递费用
     */
    private BigDecimal expPricess;


}
