package com.team.est.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private Long id;

    private String account;
    private String userName;

    private Integer sex;
    private String faceUrl;
    //添加一个token属性
    private String token;

    public UserDto(Long id, String account, String userName, Integer sex, String faceUrl) {
        this.id = id;
        this.account = account;
        this.userName = userName;
        this.sex = sex;
        this.faceUrl = faceUrl;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
