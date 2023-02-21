package com.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiroUser {
    private Integer id;
    private String name;
    private String pwd;
    private Integer rid;
}
