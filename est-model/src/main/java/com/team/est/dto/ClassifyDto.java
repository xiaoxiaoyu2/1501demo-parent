package com.team.est.dto;

import java.io.Serializable;
import java.util.List;

public class ClassifyDto implements Serializable {
    private Long id;
    private String classifyName;
    private String icon;
    private String bigIcon;
    //添加品牌集合
    private List<BrandDto> brandList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public List<BrandDto> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandDto> brandList) {
        this.brandList = brandList;
    }
}
