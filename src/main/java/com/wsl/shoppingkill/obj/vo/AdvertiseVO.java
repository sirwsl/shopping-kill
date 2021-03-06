package com.wsl.shoppingkill.obj.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/** 活动连接展示
 * @author WangShilei
 * @date 2020/11/9-18:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AdvertiseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告id
     */
    private Long id;

    /**
     * 广告图片链接
     */
    private String imgUrl;

    /**
     * 目标url
     */
    private String targetUrl;

}
