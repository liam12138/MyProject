package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGroupList {

    private Integer groupId;
    private String groupName;
    private String groupDesc;
    private Date groupCreateTime;
    private String createName;
    private Integer personNum;
    private Integer adminNum;
}
