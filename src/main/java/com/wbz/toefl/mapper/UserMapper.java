package com.wbz.toefl.mapper;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.GroupList;
import com.wbz.toefl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User userLogin(@Param("name") String name, @Param("password") String password);

    Page<GroupList> getMyGroup(@Param("userId") Integer userId);

    Page<GroupList> getMyCreate(@Param("userId") Integer userId);
}
