package com.sign.signin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 平时成绩
 * regular_grade
 */
public class RegularGrade implements Serializable {
    /**
     * 学生id
     */
    private String userid;

    /**
     * 考勤分成绩
     */
    private BigDecimal attendanceScore;

    /**
     * 汇报获得分数
     */
    private BigDecimal reportScore;

    /**
     * 作业成绩
     */
    private BigDecimal workScore;

    /**
     * 总成绩
     */
    private BigDecimal totalScore;

    private static final long serialVersionUID = 1L;


    public RegularGrade() {
    }

    public RegularGrade(String userid, BigDecimal attendanceScore, BigDecimal reportScore, BigDecimal workScore, BigDecimal totalScore, long serialVersionUID) {
        this.userid = userid;
        this.attendanceScore = attendanceScore;
        this.reportScore = reportScore;
        this.workScore = workScore;
        this.totalScore = totalScore;
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
     * @return attendanceScore
     */
    public BigDecimal getAttendanceScore() {
        return attendanceScore;
    }

    /**
     * 设置
     * @param attendanceScore
     */
    public void setAttendanceScore(BigDecimal attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    /**
     * 获取
     * @return reportScore
     */
    public BigDecimal getReportScore() {
        return reportScore;
    }

    /**
     * 设置
     * @param reportScore
     */
    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    /**
     * 获取
     * @return workScore
     */
    public BigDecimal getWorkScore() {
        return workScore;
    }

    /**
     * 设置
     * @param workScore
     */
    public void setWorkScore(BigDecimal workScore) {
        this.workScore = workScore;
    }

    /**
     * 获取
     * @return totalScore
     */
    public BigDecimal getTotalScore() {
        return totalScore;
    }

    /**
     * 设置
     * @param totalScore
     */
    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String toString() {
        return "RegularGrade{userid = " + userid + ", attendanceScore = " + attendanceScore + ", reportScore = " + reportScore + ", workScore = " + workScore + ", totalScore = " + totalScore + ", serialVersionUID = " + serialVersionUID + "}";
    }
}