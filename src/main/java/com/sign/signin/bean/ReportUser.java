package com.sign.signin.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 汇报和学生关系表
 * report_user
 */
public class ReportUser implements Serializable {
    /**
     * 学生id
     */
    private String userid;

    /**
     * 汇报id
     */
    private Long reportId;

    /**
     * 等级id
     */
    private Integer weightId;


    private static final long serialVersionUID = 1L;

    public ReportUser() {
    }

    public ReportUser(String userid, Long reportId, Integer weightId) {
        this.userid = userid;
        this.reportId = reportId;
        this.weightId = weightId;
    }

    /**
     * 获取
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取
     * @return reportId
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * 设置
     * @param reportId
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public String toString() {
        return "ReportUser{userid = " + userid + ", reportId = " + reportId + ", weightId = " + weightId + "}";
    }
}