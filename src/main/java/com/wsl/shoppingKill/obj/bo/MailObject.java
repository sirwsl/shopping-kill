package com.wsl.shoppingKill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/** 邮件实体类封装
 * @author : WangShiLei
 * @date : 2020/11/25 9:04 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class MailObject implements Serializable {

    /**
     * 模板
     */
    private String template;

    /**
     * 收件人
     */
    private String number;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private Map<String,String> content;

    /**
     * 附件
     */
    private File file;

    /**
     * 附件名称
     */
    private String fileName;


}
