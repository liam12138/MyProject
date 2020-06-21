package com.wbz.toefl.mapper;

import com.wbz.toefl.entity.GroupType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupTypeMapper {
    List<GroupType> getAllGroupType();

    GroupType getGroupTypeById(@Param("id") Integer id);
}
