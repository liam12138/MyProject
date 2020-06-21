package com.wbz.toefl.service;

import com.wbz.toefl.entity.GroupType;

import java.util.List;

public interface GroupTypeService {

    List<GroupType> getAllGroupType();

    GroupType getGroupTypeById(Integer id);
}
