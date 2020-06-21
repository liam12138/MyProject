package com.wbz.toefl.service.impl;

import com.wbz.toefl.entity.GroupType;
import com.wbz.toefl.mapper.GroupTypeMapper;
import com.wbz.toefl.service.GroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupTypeServiceImpl implements GroupTypeService {

    @Autowired
    private GroupTypeMapper groupTypeMapper;

    @Override
    public List<GroupType> getAllGroupType() {
        return groupTypeMapper.getAllGroupType();
    }

    @Override
    public GroupType getGroupTypeById(Integer id) {
        return groupTypeMapper.getGroupTypeById(id);
    }
}
