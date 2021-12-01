package com.team.est.dto;

import java.io.Serializable;
import java.util.List;

public class MalfunctionDto implements Serializable {
    private Long id;
    private String title;
    private Integer isHint;
    private String hintTitle;
    private String hintInfo;
    private String hintImg;
    //添加故障明细  一对多
    private List<MalfunctionOptionDto> optionsList;

    public MalfunctionDto() {
    }

    public MalfunctionDto(Long id, String title, Integer isHint, String hintTitle, String hintInfo, String hintImg, List<MalfunctionOptionDto> optionsList) {
        this.id = id;
        this.title = title;
        this.isHint = isHint;
        this.hintTitle = hintTitle;
        this.hintInfo = hintInfo;
        this.hintImg = hintImg;
        this.optionsList = optionsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsHint() {
        return isHint;
    }

    public void setIsHint(Integer isHint) {
        this.isHint = isHint;
    }

    public String getHintTitle() {
        return hintTitle;
    }

    public void setHintTitle(String hintTitle) {
        this.hintTitle = hintTitle;
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

    public List<MalfunctionOptionDto> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<MalfunctionOptionDto> optionsList) {
        this.optionsList = optionsList;
    }
}
