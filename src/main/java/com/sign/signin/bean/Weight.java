package com.sign.signin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * weight
 */
public class Weight implements Serializable {
    private Integer weightId;

    private String type;

    private BigDecimal proportion;

    private static final long serialVersionUID = 1L;

    public Weight() {
    }

    public Weight(Integer weightId, String type, BigDecimal proportion) {
        this.weightId = weightId;
        this.type = type;
        this.proportion = proportion;
    }

    /**
     * 获取
     * @return weightId
     */
    public Integer getWeightId() {
        return weightId;
    }

    /**
     * 设置
     * @param weightId
     */
    public void setWeightId(Integer weightId) {
        this.weightId = weightId;
    }

    /**
     * 获取
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取
     * @return proportion
     */
    public BigDecimal getProportion() {
        return proportion;
    }

    /**
     * 设置
     * @param proportion
     */
    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public String toString() {
        return "Weight{weightId = " + weightId + ", type = " + type + ", proportion = " + proportion + "}";
    }
}