package com.wsl.shoppingKill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 用户基本信息VO
 * @author : WangShiLei
 * @date : 2020/11/7 4:23 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String name;
    private String url;
}
