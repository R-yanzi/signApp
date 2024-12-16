package com.sign.signin.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * 汇报成绩记录表
 * report
 */
public class Report implements Serializable {
    /**
     * 汇报id
     */
    private Long reportId;

    /**
     * 汇报时间
     */
    private Date reportTime;

    /**
     * 已经打分了的人数
     */
    private Long reported;

    /**
     * 手写构造函数
     */
    public Report(Long reportId) {
        this.reportId = reportId;
    }

    private static final long serialVersionUID = 1L;

    public Report() {
    }

    public Report(Long reportId, Date reportTime, Long reported, long serialVersionUID) {
        this.reportId = reportId;
        this.reportTime = reportTime;
        this.reported = reported;
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
     * @return reportTime
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 设置
     * @param reportTime
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 获取
     * @return reported
     */
    public Long getReported() {
        return reported;
    }

    /**
     * 设置
     * @param reported
     */
    public void setReported(Long reported) {
        this.reported = reported;
    }

    public String toString() {
        return "Report{reportId = " + reportId + ", reportTime = " + reportTime + ", reported = " + reported + ", serialVersionUID = " + serialVersionUID + "}";
    }
}