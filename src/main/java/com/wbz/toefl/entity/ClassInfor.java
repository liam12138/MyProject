package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfor {
    private Integer classId;
    private String className;
    private String classType;
    private String classTeacher;
    private String startDate;
    private String startTime;
    private String endTime;
}
