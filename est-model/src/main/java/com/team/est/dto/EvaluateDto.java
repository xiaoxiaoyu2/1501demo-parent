package com.team.est.dto;

import java.math.BigDecimal;
import java.util.List;

public class EvaluateDto {
    private Long id;
    private Long modelId;
    private BigDecimal subscription;
    private BigDecimal price;

    private ModelDto model;  //商品型号
    private List<EvaluateDetailDto> detailList; //明细

    public EvaluateDto() {
    }

    public EvaluateDto(Long id, Long modelId, BigDecimal subscription, BigDecimal price) {
        this.id = id;
        this.modelId = modelId;
        this.subscription = subscription;
        this.price = price;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public List<EvaluateDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<EvaluateDetailDto> detailList) {
        this.detailList = detailList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public BigDecimal getSubscription() {
        return subscription;
    }

    public void setSubscription(BigDecimal subscription) {
        this.subscription = subscription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
