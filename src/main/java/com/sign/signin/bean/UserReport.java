package com.sign.signin.bean;

import java.math.BigDecimal;

/**
 * 汇报打分时的封装类
 */
public class UserReport {

    private User user;

    private String type;

    public UserReport() {
    }

    public UserReport(User user, String type) {
        this.user = user;
        this.type = type;
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

    public String toString() {
        return "UserReport{user = " + user + ", type = " + type + "}";
    }
}
