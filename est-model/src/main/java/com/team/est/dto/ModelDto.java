package com.team.est.dto;

import java.io.Serializable;

public class ModelDto implements Serializable {
    private Long id;
    private String modelName;
    private String faceImg;
    private String contentImg;

    public ModelDto(Long id, String modelName, String faceImg, String contentImg) {
        this.id = id;
        this.modelName = modelName;
        this.faceImg = faceImg;
        this.contentImg = contentImg;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public ModelDto(Long id, String modelName, String faceImg) {
        this.id = id;
        this.modelName = modelName;
        this.faceImg = faceImg;
    }

    public ModelDto() {
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
