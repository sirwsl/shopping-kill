package com.wsl.shoppingkill.obj.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 *
 * 黑名单与白名单Param
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/
@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class LimitListParam {

    /**
     * id
     **/
    private Long id;

    /**
     * 类型(0-手机号 1-ip)
     */
    private Integer type;

    /**
     * 号码
     */
    @NotBlank(message = "添加黑名单不能为空")
    @NotEmpty(message = "添加黑名单不能为空")
    private String number;


    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}