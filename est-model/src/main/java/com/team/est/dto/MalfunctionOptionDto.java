package com.team.est.dto;

import java.io.Serializable;

public class MalfunctionOptionDto implements Serializable {
    private Long id;
    private String optionName;
    private Integer isHint;
    private String hintInfo;
    private String hintImg;

    public MalfunctionOptionDto() {
    }

    public MalfunctionOptionDto(Long id, String optionName, Integer isHint, String hintInfo, String hintImg) {
        this.id = id;
        this.optionName = optionName;
        this.isHint = isHint;
        this.hintInfo = hintInfo;
        this.hintImg = hintImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getIsHint() {
        return isHint;
    }

    public void setIsHint(Integer isHint) {
        this.isHint = isHint;
    }

    public String getHintInfo() {
        return hintInfo;
    }

    public void setHintInfo(String hintInfo) {
        this.hintInfo = hintInfo;
    }

    public String getHintImg() {
        return hintImg;
    }

    public void setHintImg(String hintImg) {
        this.hintImg = hintImg;
    }
}
