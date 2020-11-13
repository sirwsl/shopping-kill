package com.wsl.shoppingKill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author WangShilei
 * @date 2020/11/9-10:45
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String url;
    private Integer flag;


    public static final Integer CUSTOMER = 0;
    public static final Integer ADMIN = 1;

}
