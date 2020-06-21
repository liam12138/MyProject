package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicClass {
    private Integer classId;
    private String className;
    private String classDate;
    private String startTime;
    private String endTime;
    private String imgUrl;
    private String classGuide;
    private Integer regNum;
    private Integer teacherId;
    private String teacherImg;
    private String teacherName;
    private Integer phraseNum;
    private String introduce;
}
