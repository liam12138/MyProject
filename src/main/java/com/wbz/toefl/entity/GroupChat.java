package com.wbz.toefl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChat {
    private Integer chatId;
    private String userName;
    private String userImg;
    private String article;
    private Date createTime;
    private Integer isMe;
    private Integer type;
}
