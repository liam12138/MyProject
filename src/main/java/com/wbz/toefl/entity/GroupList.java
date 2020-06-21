package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupList {

    private Integer groupId;
    private String groupName;
    private Integer groupType;
    private String typeName;
    private String groupRule;
    private String imgUrl;
    private String groupDesc;
    private Date createTime;
    private Integer personNum;
    private Integer myStatus;
}
