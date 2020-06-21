package com.wbz.toefl.mapper;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface GroupMapper {
    Page<GroupList> getAll(@Param("myId") Integer myId);

    Page<GroupList> getByType(@Param("type") Integer type, @Param("myId") Integer myId);

    List<AdminGroupList> getAdminGroupList();

    List<AdminGroupList> getAdminGroupListByName(@Param("name") String name);

    List<AdminGroupList> getAdminGroupListByDate(@Param("date") String date);

    List<AdminGroupList> getAdminGroupListByNameAndDate(@Param("name") String name, @Param("date") String date);

    void deleteGroupById(@Param("id") Integer id);

    GroupList getGroupById(@Param("id") Integer id, @Param("myId") Integer myId);

    List<GroupUser> getGroupAdmin(@Param("groupId") Integer groupId);

    List<GroupUser> getGroupUser(@Param("groupId") Integer groupId);

    void groupAdd(@Param("id") Integer id, @Param("groupId") Integer groupId, @Param("createTime") Date date);

    List<GroupChat> getGroupChat(@Param("groupId") Integer groupId, @Param("userId") Integer userId);

    void insertChat(@Param("groupId") Integer groupId, @Param("date") Date date, @Param("userId") Integer userId);

    void delChat(@Param("chatId") Integer chatId);

    void modChat(@Param("chatId") Integer chatId, @Param("content") String content);

    void exitGroup(@Param("groupId") Integer groupId, @Param("userId") Integer userId);

    void delChatByGroupIdAndUserId(@Param("groupId") Integer groupId, @Param("userId") Integer userId);

    void createGroup(Group group);
}
