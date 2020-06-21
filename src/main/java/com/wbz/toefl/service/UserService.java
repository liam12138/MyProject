package com.wbz.toefl.service;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.GroupList;
import com.wbz.toefl.entity.User;

public interface UserService {

    User userLogin(String name, String password);

    Page<GroupList> getMyGroup(Integer userId);

    Page<GroupList> getMyCreate(Integer userId);
}
