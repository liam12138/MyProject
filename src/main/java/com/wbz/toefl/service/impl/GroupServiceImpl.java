package com.wbz.toefl.service.impl;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;
import com.wbz.toefl.mapper.GroupMapper;
import com.wbz.toefl.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Page<GroupList> getAll(Integer myId) {
        return groupMapper.getAll(myId);
    }

    @Override
    public Page<GroupList> getByType(Integer type, Integer myId) {
        return groupMapper.getByType(type, myId);
    }

    @Override
    public List<AdminGroupList> getAdminGroupList() {
        return groupMapper.getAdminGroupList();
    }

    @Override
    public List<AdminGroupList> getAdminGroupListByName(String name) {
        return groupMapper.getAdminGroupListByName(name);
    }

    @Override
    public List<AdminGroupList> getAdminGroupListByDate(String date) {
        return groupMapper.getAdminGroupListByDate(date);
    }

    @Override
    public List<AdminGroupList> getAdminGroupListByNameAndDate(String name, String date) {
        return groupMapper.getAdminGroupListByNameAndDate(name, date);
    }

    @Override
    public void deleteGroupById(Integer id) {
        groupMapper.deleteGroupById(id);
    }

    @Override
    public GroupList getGroupById(Integer id, Integer myId) {
        return groupMapper.getGroupById(id, myId);
    }

    @Override
    public List<GroupUser> getGroupAdmin(Integer groupId) {
        return groupMapper.getGroupAdmin(groupId);
    }

    @Override
    public List<GroupUser> getGroupUser(Integer groupId) {
        return groupMapper.getGroupUser(groupId);
    }

    @Override
    public void groupAdd(Integer id, Integer groupId, Date date) {
        groupMapper.groupAdd(id, groupId, date);
    }

    @Override
    public List<GroupChat> getGroupChat(Integer groupId, Integer userId) {
        return groupMapper.getGroupChat(groupId, userId);
    }

    @Override
    public void insertChat(Integer groupId, Date date, Integer userId) {
        groupMapper.insertChat(groupId, date, userId);
    }

    @Override
    public void delChat(Integer chatId) {
        groupMapper.delChat(chatId);
    }

    @Override
    public void modChat(Integer chatId, String content) {
        groupMapper.modChat(chatId, content);
    }

    @Override
    public void exitGroup(Integer groupId, Integer userId) {
        groupMapper.exitGroup(groupId, userId);
    }

    @Override
    public void delChatByGroupIdAndUserId(Integer groupId, Integer userId) {
        groupMapper.delChatByGroupIdAndUserId(groupId, userId);
    }

    @Override
    public void createGroup(Group group) {
        groupMapper.createGroup(group);
    }
}
