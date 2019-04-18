package com.example.mmall.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class MmallUser {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    /** 鉴权token **/
    private String token;
}