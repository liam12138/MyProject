package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDetail {

    private Integer teacherId;
    private String loginName;
    private String userName;
    private String email;
    private String phoneNum;
    private String birthday;
    private String introduce;
    private String imgUrl;
    private Integer articleNum;
    private Integer publicClassNum;
    private Integer castleClassNum;
    private Integer phraseNum;
    private Integer status;
}
