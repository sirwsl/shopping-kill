package com.wsl.shoppingKill.constant;

import lombok.Getter;

/**
 * 文件名后缀枚举类
 *
 * @author caibenhao
 * @date 2020-10-29
 * @since 1.0.0
 */
@Getter
public enum FileNameSuffixEnum {
    
    /**
     * 文件后缀名
     */
    BMP(".bmp", "bmp文件"),
    GIF(".gif", "gif文件"),
    JPEG(".jpeg", "jpeg文件"),
    JPG(".jpg", "jpg文件"),
    PNG(".png", "png文件"),
    HTML(".html", "HTML文件"),
    TXT(".txt", "txt文件"),
    VSD(".vsd", "vsd文件"),
    PPTX(".pptx", "PPTX文件"),
    DOCX(".docx", "DOCX文件"),
    PPT(".ppt", "PPT文件"),
    DOC(".doc", "DOC文件"),
    XML(".xml", "XML文件"),
    PDF(".pdf", "PDF文件");
    
    /**
     * 后缀名
     */
    private final String suffix;
    
    /**
     * 描述
     */
    private final String description;
    
    FileNameSuffixEnum(String suffix, String description) {
        this.suffix = suffix;
        this.description = description;
    }
}
