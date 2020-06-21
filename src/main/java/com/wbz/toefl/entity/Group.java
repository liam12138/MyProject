package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Integer groupType;
    private String groupName;
    private String imgUrl;
    private String groupDesc;
    private String groupRule;
    private Integer groupTeacher;
    private Integer status;
    private Date createTime;
}
