package com.wbz.toefl.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbz.toefl.constant.Constant;
import com.wbz.toefl.entity.Group;
import com.wbz.toefl.entity.GroupList;
import com.wbz.toefl.entity.GroupType;
import com.wbz.toefl.entity.User;
import com.wbz.toefl.service.GroupService;
import com.wbz.toefl.service.GroupTypeService;
import com.wbz.toefl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupTypeService groupTypeService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User result = userService.userLogin(username, password);

        if ("".equals(result) || result == null) {
            // 登录失败
            return "login";
        } else {
            // 登录成功
            session.setAttribute("loginUser", result);
            return "redirect:/index.html";
        }
    }

    /**
     * 个人中心
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/user/index")
    public String userIndex(HttpSession session,
                            Model model) {


        return "personal_data/user_index";
    }

    /**
     * 个人信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/user/userInfo")
    public String userInfo(HttpSession session, Model model) {

        return "personal_data/user_info";
    }

    @RequestMapping("/user/myGroup/{page}")
    public String myGroup(
            @PathVariable("page") Integer page,
            HttpSession session, Model model) {

        User user = (User) session.getAttribute("loginUser");


        PageHelper.startPage(page, Constant.PAGESIZE);

        Page<GroupList> groupLists = userService.getMyGroup(user.getId());
        Page<GroupList> myCreate = userService.getMyCreate(user.getId());
        List<GroupType> types = groupTypeService.getAllGroupType();

        PageInfo<GroupList> pageInfo = new PageInfo<>(groupLists);

        model.addAttribute("groupLists", groupLists);
        model.addAttribute("myCreate", myCreate);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("msg", 1);
        model.addAttribute("types", types);
        return "personal_data/myGroup";
    }

    /**
     * 退出群聊
     * @param groupId
     * @param session
     * @return
     */
    @RequestMapping("/user/group/exit/{groupId}")
    public String exitGroup(@PathVariable("groupId") Integer groupId,
                            HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        groupService.exitGroup(groupId, user.getId());
        groupService.delChatByGroupIdAndUserId(groupId, user.getId());

        return "redirect:/user/myGroup/1";
    }

    @RequestMapping("/user/group/createGroup")
    public String createMyGroup(HttpSession session,
                                @PathParam("groupName") String groupName,
                                @PathParam("type") Integer type,
                                @PathParam("groupDesc") String groupDesc,
                                @PathParam("groupRule") String groupRule) {
        User user = (User) session.getAttribute("loginUser");

        Group group = new Group(type, groupName, "https://up.enterdesk.com/edpic/a5/6d/7a/a56d7acfa5df7b8a30da143bddd290e2.jpg",
                groupDesc, groupRule, user.getId(), 0, new Date(new java.util.Date().getTime()));

        groupService.createGroup(group);

        return "redirect:/user/myGroup/1";
    }

    @RequestMapping("/user/myPublicClass")
    public String myPublicClass() {
        return "personal_data/my_public_class";
    }


    @RequestMapping("/user/publicClassList")
    public String publicClassList() {
        return "redirect:/lastClass";
    }

}
