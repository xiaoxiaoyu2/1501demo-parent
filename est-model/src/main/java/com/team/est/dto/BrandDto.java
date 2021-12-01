package com.team.est.dto;

import java.io.Serializable;
import java.util.List;

public class BrandDto implements Serializable {
    private Long id;
    private String brandName;
    private String logo;
    //商品的集合
    private List<ModelDto> modelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ModelDto> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelDto> modelList) {
        this.modelList = modelList;
    }
}
