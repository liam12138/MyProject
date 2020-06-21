package com.wbz.toefl.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbz.toefl.constant.Constant;
import com.wbz.toefl.entity.*;
import com.wbz.toefl.service.PublicClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class PublicClassController {

    @Autowired
    private PublicClassService publicClassService;

    /**
     * 返回所有公开课信息
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/admin/openClass/{page}")
    public String openClass(@PathVariable("page") Integer page,
                            Model model) {

        PageHelper.startPage(page, Constant.PAGESIZE);
        PageInfo<ClassInfor> pageInfo = null;

        Page<ClassInfor> allClass = publicClassService.getAllClass();
        List<Teacher> teachers = publicClassService.getTeacher();
        List<ClassType> types = publicClassService.getClassType();

        pageInfo = new PageInfo<>(allClass);

        model.addAttribute("allClass", allClass);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teachers", teachers);
        model.addAttribute("types", types);
        model.addAttribute("msg", 1);

        return "master/openClass";
    }

    /**
     * 条件筛选
     * @param className
     * @param teacherId
     * @param typeId
     * @param model
     * @return
     */
    @GetMapping("/admin/open/search")
    public String openClass(@PathParam("className") String className,
                            @PathParam("teacherId") Integer teacherId,
                            @PathParam("typeId") Integer typeId,
                            Model model) {

        Page<ClassInfo> classByCondition = publicClassService.getClassByCondition(className, teacherId, typeId);
        List<Teacher> teachers = publicClassService.getTeacher();
        List<ClassType> types = publicClassService.getClassType();

        model.addAttribute("allClass", classByCondition);
        model.addAttribute("teachers", teachers);
        model.addAttribute("types", types);

        return "master/openClass";
    }

    /**
     * 删除课程
     * @param classId
     * @param model
     * @return
     */
    @PostMapping("/admin/class/del")
    public String classDel(@PathParam("classId") Integer[] classId,
                           Model model) {

        if (classId != null) {
            for (Integer id : classId) {
                publicClassService.delClass(id);
            }
        }

        return "redirect:/admin/openClass/1";
    }

    @RequestMapping("/lastClass")
    public String lastClass(Model model) {
        model.addAttribute("msg", 1);
        return "open_class/public_class_list";
    }

    /**
     * 获取最新公开课
     * @param model
     * @param type
     * @return
     */
    @RequestMapping("/last_class/{type}")
    public String lastClass(Model model,
                            @PathVariable("type") Integer type) {

        List<PublicClass> lastClass = publicClassService.getLastClass();

        model.addAttribute("lastClass", lastClass);
        return "open_class/last_class";
    }

    /**
     * 名师列表推荐
     * @param model
     * @return
     */
    @RequestMapping("/teacher/recommend/{page}")
    public String teacherRecommend(Model model,
                                   @PathVariable("page") Integer page) {

        PageHelper.startPage(page, Constant.PAGESIZE);

        Page<TeacherDetail> teachers = publicClassService.getAllTeacher();

        PageInfo<TeacherDetail> pageInfo = new PageInfo<>(teachers);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teachers", teachers);
        return "open_class/teacher_recommend";
    }

    /**
     * 获取老师
     * @param teacherId
     * @param model
     * @return
     */
    @RequestMapping("/teacher/teacherDetail/{teacherId}")
    public String teacherDetail(@PathVariable("teacherId") Integer teacherId,
                                Model model) {
        TeacherDetail teacher = publicClassService.getTeacherById(teacherId);

        model.addAttribute("teacher", teacher);
        System.out.println(teacherId);
        return "open_class/one_teacher_recommend";
    }

    /**
     * 支付订单
     * @param classId
     * @return
     */
    @RequestMapping("/payment/payment1/{classId}")
    public String payment1(@PathVariable("classId") Integer classId) {
        System.out.println(classId);
        return "payment/payment1";
    }

    @RequestMapping("/classDetail/{classId}")
    public String classDetail(@PathVariable("classId") Integer classId) {
        System.out.println(classId);
        return "open_class/class_detail";
    }

    @RequestMapping("/classExplain")
    public String classExplain() {
        return "open_class/class_explain";
    }

    @RequestMapping("/payment2")
    public String payment2() {
        return "payment/payment2";
    }

    @RequestMapping("/payment3")
    public String payment3() {
        return "payment/payment3";
    }

    @RequestMapping("/classPut")
    public String classPut() {
        return "open_class/class_put";
    }
}
