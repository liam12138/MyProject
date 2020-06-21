package com.wbz.toefl.mapper;

import com.github.pagehelper.Page;
import com.wbz.toefl.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;

@Mapper
public interface PublicClassMapper {

    Page<ClassInfor> getAllClass();

    Page<ClassInfo> getClassByCondition(@Param("className") String className, @Param("teacherId") Integer teacherName, @Param("classTypeId") Integer classTypeId);

    List<Teacher> getTeacher();

    List<ClassType> getClassType();

    void delClass(@Param("classId") Integer classId);

    List<PublicClass> getLastClass();

    Page<TeacherDetail> getAllTeacher();

    TeacherDetail getTeacherById(@Param("teacherId") Integer teacherId);
}
