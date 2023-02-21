package com.shiro.service;

import com.shiro.entity.ShiroUser;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:28
 */
public interface ShiroUserService {
    // 用户登陆
    ShiroUser getUserByName(String name);
}
