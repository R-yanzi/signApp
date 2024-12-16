package com.sign.signin.bean;

import java.math.BigDecimal;

public class Grade {
    private User user;

    private BigDecimal attendanceScore;

    private BigDecimal reportScore;

    private BigDecimal workScore;

    public Grade() {
    }

    public Grade(User user, BigDecimal attendanceScore, BigDecimal reportScore, BigDecimal workScore) {
        this.user = user;
        this.attendanceScore = attendanceScore;
        this.reportScore = reportScore;
        this.workScore = workScore;
    }

    /**
     * 获取
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
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

    public String toString() {
        return "Grade{user = " + user + ", attendanceScore = " + attendanceScore + ", reportScore = " + reportScore + ", workScore = " + workScore + "}";
    }
}
