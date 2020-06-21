package com.wbz.toefl.service.impl;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.GroupList;
import com.wbz.toefl.entity.User;
import com.wbz.toefl.mapper.UserMapper;
import com.wbz.toefl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String name, String password) {
        return userMapper.userLogin(name, password);
    }

    @Override
    public Page<GroupList> getMyGroup(Integer userId) {
        return userMapper.getMyGroup(userId);
    }

    @Override
    public Page<GroupList> getMyCreate(Integer userId) {
        return userMapper.getMyCreate(userId);
    }
}
