package com.team.est.dto;

public class EvaluateDetailDto {
    private Long id;
    private Long optionId;
    private Long malfId;
    private String optionName;

    public EvaluateDetailDto() {
    }

    public EvaluateDetailDto(Long id, Long optionId, Long malfId, String optionName) {
        this.id = id;
        this.optionId = optionId;
        this.malfId = malfId;
        this.optionName = optionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getMalfId() {
        return malfId;
    }

    public void setMalfId(Long malfId) {
        this.malfId = malfId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
