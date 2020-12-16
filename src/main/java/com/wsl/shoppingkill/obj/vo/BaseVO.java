package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author WangShilei
 * @date 2020/12/16-10:06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
