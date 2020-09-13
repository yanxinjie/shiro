package com.yxj.shiro.service.impl;

import com.yxj.shiro.domain.Role;
import com.yxj.shiro.domain.User;
import com.yxj.shiro.mapper.RoleMapper;
import com.yxj.shiro.mapper.UserMapper;
import com.yxj.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 严新捷
 * @Type UserServiceImpl.java
 * @Desc
 * @date 2020/9/13 14:32
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUsername(username);

        //用户的角色集合
        List<Role> roleList =  roleMapper.findRoleListByUserId(user.getId());


        user.setRoleList(roleList);

        return user;
    }


    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }


    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
