package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品VO
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class GoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @NotNull(message = "商品ID不能为空")
    @NotEmpty(message = "商品ID不能为空")
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
     * 类别名称
     */
    private String typeName;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 是否上架
     */
    private Boolean shelf;

    /**
     * 商品描述
     */
    private String detail;

    private MultipartFile files;

    private List<Sku> skuList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sku{

        private Long id;

        private String attribute;

        private Integer num;
    }

}