package com.wbz.toefl.service;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;

public interface PublicClassService {

    Page<ClassInfor> getAllClass();

    Page<ClassInfo> getClassByCondition(String className, Integer teacherName, Integer classTypeId);

    List<Teacher> getTeacher();

    List<ClassType> getClassType();

    void delClass(Integer classId);

    List<PublicClass> getLastClass();

    TeacherDetail getTeacherById(Integer teacherId);

    Page<TeacherDetail> getAllTeacher();
}
