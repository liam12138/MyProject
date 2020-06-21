package com.wbz.toefl.service.impl;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;
import com.wbz.toefl.mapper.PublicClassMapper;
import com.wbz.toefl.service.PublicClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicClassServiceImpl implements PublicClassService {

    @Autowired
    PublicClassMapper publicClassMapper;

    @Override
    public Page<ClassInfor> getAllClass() {
        return publicClassMapper.getAllClass();
    }

    @Override
    public Page<ClassInfo> getClassByCondition(String className, Integer teacherName, Integer classTypeId) {
        return publicClassMapper.getClassByCondition(className, teacherName, classTypeId);
    }

    @Override
    public List<Teacher> getTeacher() {
        return publicClassMapper.getTeacher();
    }

    @Override
    public List<ClassType> getClassType() {
        return publicClassMapper.getClassType();
    }

    @Override
    public void delClass(Integer classId) {
        publicClassMapper.delClass(classId);
    }

    @Override
    public List<PublicClass> getLastClass() {
        return publicClassMapper.getLastClass();
    }

    @Override
    public TeacherDetail getTeacherById(Integer teacherId) {
        return publicClassMapper.getTeacherById(teacherId);
    }

    @Override
    public Page<TeacherDetail> getAllTeacher() {
        return publicClassMapper.getAllTeacher();
    }
}
