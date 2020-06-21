package com.wbz.toefl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbz.toefl.constant.Constant;
import com.wbz.toefl.entity.*;
import com.wbz.toefl.service.GroupService;
import com.wbz.toefl.service.GroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupTypeService groupTypeService;

    @RequestMapping("/group/get/{type}/{page}")
    public String groupIndex(
            @PathVariable("page") Integer page,
            @PathVariable("type") Integer type,
            HttpSession session,
            Model model,
            Map<String, String> map) {
        User username = (User) session.getAttribute("loginUser");

        if (username == null) {
            return "login";
        } else {
            PageInfo<GroupList> pageInfo = null;
            List<GroupList> groups = null;
            PageHelper.startPage(page, 10);
            if (type == 0) {
                groups = groupService.getAll(username.getId());
            } else {
                groups = groupService.getByType(type, username.getId());
            }

            pageInfo = new PageInfo<GroupList>(groups);
            model.addAttribute("groups", groups);
            model.addAttribute("pageInfo", pageInfo);

            map.put("msg", type.toString());
            return "group/groupIndex";
        }
    }

    /**
     * 进入创建群聊页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/group/create")
    public String createGroup(HttpSession session,
                              Model model) {
        Collection<GroupType> groupTypes = groupTypeService.getAllGroupType();

        model.addAttribute("types", groupTypes);
        return "group/groupCreate";
    }

    /**
     * 创建群聊
     * @param session
     * @param groupName
     * @param type
     * @param groupDesc
     * @param groupRule
     * @return
     */
    @RequestMapping("/group/createGroup")
    public String createMyGroup(HttpSession session,
                                @PathParam("groupName") String groupName,
                                @PathParam("type") Integer type,
                                @PathParam("groupDesc") String groupDesc,
                                @PathParam("groupRule") String groupRule) {
        User user = (User) session.getAttribute("loginUser");

        Group group = new Group(type, groupName, "https://up.enterdesk.com/edpic/a5/6d/7a/a56d7acfa5df7b8a30da143bddd290e2.jpg",
                groupDesc, groupRule, user.getId(), 0, new Date(new java.util.Date().getTime()));

        groupService.createGroup(group);

        return "redirect:/group/get/0/1";
    }

    @PostMapping("/createGroup")
    public String createAGroup(HttpSession session,
                               @PathParam("groupName") String groupName,
                               @PathParam("groupRule") String groupRule,
                               @PathParam("type") Integer type,
                               @PathParam("groupDesc") String groupDesc) {

        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupType(type);
        group.setGroupDesc(groupDesc);

        return "group/groupIndex";
    }

    @RequestMapping("/admin/groupList/{page}")
    public String masterGroup(@PathVariable("page") Integer page,
                              Model model,
                              Map<String, Object> map) {
        List<AdminGroupList> adminGroupList = groupService.getAdminGroupList();

        map.put("size", (int) Math.ceil(adminGroupList.size()/Constant.PAGESIZE));
        map.put("page", page);

        Integer max = page * Constant.PAGESIZE;

        if (max > adminGroupList.size()) {
            max = adminGroupList.size();
        }

        adminGroupList = adminGroupList.subList((page - 1) * Constant.PAGESIZE, max);

        model.addAttribute("adminGroupList", adminGroupList);
        return "master/groupList";
    }

    @GetMapping("/admin/groupListWithParam")
    public String masterGroup(@PathParam("name") String name,
                              @PathParam("date") String date,
                              Model model,
                              Map<String, Object> map) {

        Collection<AdminGroupList> adminGroupList = null;

        if ((name == null || name.equals("")) && (date == null || date.equals(""))) {
            adminGroupList = groupService.getAdminGroupList();
        } else if (!name.equals("") && (date == null || date.equals(""))) {
            String Rname = "%"+name+"%";
            adminGroupList = groupService.getAdminGroupListByName(Rname);
        } else if ((name == null || name.equals("")) && !date.equals("")) {
            adminGroupList = groupService.getAdminGroupListByDate(date);
        } else {
            String Rname = "%"+name+"%";
            adminGroupList = groupService.getAdminGroupListByNameAndDate(Rname, date);
        }


        map.put("name", name);
        map.put("date", date);
        model.addAttribute("adminGroupList", adminGroupList);
        return "master/groupList";
    }

    /**
     * 管理员删除群组
     * @param groupIds
     * @return
     */
    @GetMapping("/admin/group/del")
    public String adminGroupDel(@RequestParam("groupId") String[] groupIds) {
        if (groupIds != null) {
            for (String groupId : groupIds) {
                groupService.deleteGroupById(Integer.parseInt(groupId));
            }
        }

        return "redirect:/admin/groupList/1";
    }

    /**
     * 获取群组详情
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping("/group/info/{groupId}")
    public String getGroupInfo(
            @PathVariable("groupId") Integer groupId,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loginUser");
        GroupList group = groupService.getGroupById(groupId, user.getId());

        GroupType groupType = groupTypeService.getGroupTypeById(group.getGroupType());

        List<GroupUser> groupUsers = groupService.getGroupUser(groupId);
        List<GroupUser> groupAdmins = groupService.getGroupAdmin(groupId);

        model.addAttribute("group", group);
        model.addAttribute("groupUsers", groupUsers);
        model.addAttribute("groupAdmins", groupAdmins);
        model.addAttribute("groupType", groupType);
        return "group/groupDetail";
    }

    /**
     * 进入聊天室
     * @param form
     * @param groupId
     * @param httpSession
     * @return
     */
    @RequestMapping("/group/chat/{form}/{groupId}")
    public String groupChat(
            @PathVariable("form") Integer form,
            @PathVariable("groupId") Integer groupId,
            HttpSession httpSession,
            Model model) {
        User user = (User) httpSession.getAttribute("loginUser");

        if (form == 0) {
            groupService.groupAdd(user.getId(), groupId, new Date(new java.util.Date().getTime()));
        }

        GroupList group = groupService.getGroupById(groupId, user.getId());
        GroupType groupType = groupTypeService.getGroupTypeById(group.getGroupType());
        List<GroupUser> admins = groupService.getGroupAdmin(groupId);
        List<GroupUser> users = groupService.getGroupUser(groupId);
        List<GroupChat> groupChats = groupService.getGroupChat(groupId, user.getId());

        model.addAttribute("group", group);
        model.addAttribute("groupType", groupType);
        model.addAttribute("admins", admins);
        model.addAttribute("users", users);
        model.addAttribute("groupChats", groupChats);

        return "group/groupChat";
    }

    /**
     * 退出群聊
     * @param groupId
     * @param session
     * @return
     */
    @RequestMapping("/group/exit/{groupId}")
    public String exitGroup(@PathVariable("groupId") Integer groupId,
                            HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        groupService.exitGroup(groupId, user.getId());
        groupService.delChatByGroupIdAndUserId(groupId, user.getId());

        return "redirect:/group/get/0/1";
    }

    /**
     * 群聊发信息
     * @param groupId
     * @param content
     * @param httpSession
     * @return
     */
    @PostMapping("/group/chatAdd")
    public String chatAdd(@PathParam("groupId") Integer groupId,
                          @PathParam("content") String content,
                          HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loginUser");

        groupService.insertChat(groupId, new Date(new java.util.Date().getTime()), user.getId());

        return "redirect:/group/chat/1/" + groupId;
    }

    /**
     * 删除聊天记录
     * @param groupId
     * @param chatId
     * @param session
     * @return
     */
    @RequestMapping("/group/chatDel/{groupId}/{chatId}")
    public String chatDel(@PathVariable("groupId") Integer groupId,
                          @PathVariable("chatId") Integer chatId,
                          HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        groupService.delChat(chatId);
        return "redirect:/group/chat/1/"+groupId;
    }

    /**
     * 修改聊天页面
     * @param groupId
     * @param chatId
     * @param model
     * @return
     */
    @RequestMapping(value = "/group/chatMod/{groupId}/{chatId}")
    public String chatMod(@PathVariable("groupId") Integer groupId,
                          @PathVariable("chatId") Integer chatId,
                          Model model){
       // groupService.getChat(chatId);
        model.addAttribute("chatId", chatId);
        model.addAttribute("chatId", groupId);
        return "group/groupChatMod";
    }

    /**
     * 确认修改
     * @param chatId
     * @param groupId
     * @param content
     * @return
     */
    @PostMapping("/group/chatMod/confirm")
    public String chatMod(@PathParam("chatId") Integer chatId,
                          @PathParam("groupId") Integer groupId,
                          @PathParam("content") String content) {

        groupService.modChat(chatId, content);
        return "redirect:/group/chat/1/"+groupId;
    }

    @RequestMapping("/group/editGroup/{groupId}")
    public String editGroup(@PathVariable("groupId") Integer groupId) {
        return "group/edit_group";
    }

}
