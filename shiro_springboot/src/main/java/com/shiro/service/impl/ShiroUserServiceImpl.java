package com.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shiro.entity.ShiroUser;
import com.shiro.mapper.ShiroUserMapper;
import com.shiro.service.ShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:29
 */
@Service
public class ShiroUserServiceImpl implements ShiroUserService {

    @Autowired
    private ShiroUserMapper userMapper;

    @Override
    public ShiroUser getUserByName(String name) {
        LambdaQueryWrapper<ShiroUser> queryWrapper = Wrappers.lambdaQuery(ShiroUser.class);
        // LambdaQueryWrapper<ShiroUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShiroUser::getName, name);
        return userMapper.selectOne(queryWrapper);
    }
}
