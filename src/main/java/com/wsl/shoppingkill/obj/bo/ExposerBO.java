package com.wsl.shoppingkill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 秒杀连接BO
 * @author : WangShiLei
 * @date : 2020/12/31 11:05 上午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ExposerBO {
    /**
     * 秒杀md5加密
     */
    private String md5;

    /**
     * 秒杀时间
     */
    private Long seconds;
}
