package com.wbz.toefl.service;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;

import java.sql.Date;
import java.util.List;

public interface GroupService {
    Page<GroupList> getAll(Integer myId);

    Page<GroupList> getByType(Integer type, Integer myId);

    List<AdminGroupList> getAdminGroupList();

    List<AdminGroupList> getAdminGroupListByName(String name);

    List<AdminGroupList> getAdminGroupListByDate(String date);

    List<AdminGroupList> getAdminGroupListByNameAndDate(String name, String date);

    void deleteGroupById(Integer id);

    GroupList getGroupById(Integer id, Integer myId);

    List<GroupUser> getGroupAdmin(Integer groupId);

    List<GroupUser> getGroupUser(Integer groupId);

    void groupAdd(Integer id, Integer groupId, Date date);

    List<GroupChat> getGroupChat(Integer groupId, Integer userId);

    void insertChat(Integer groupId, Date date, Integer userId);

    void delChat(Integer chatId);

    void modChat(Integer chatId, String content);

    void exitGroup(Integer groupId, Integer userId);

    void delChatByGroupIdAndUserId(Integer groupId, Integer userId);

    void createGroup(Group group);
}
