package com.sign.signin.bean;

import java.math.BigDecimal;

public class Grade {
    private User user;

    private RegularGrade regularGrade;


    public Grade() {
    }

    public Grade(User user, RegularGrade regularGrade) {
        this.user = user;
        this.regularGrade = regularGrade;
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
     * @return regularGrade
     */
    public RegularGrade getRegularGrade() {
        return regularGrade;
    }

    /**
     * 设置
     * @param regularGrade
     */
    public void setRegularGrade(RegularGrade regularGrade) {
        this.regularGrade = regularGrade;
    }

    public String toString() {
        return "Grade{user = " + user + ", regularGrade = " + regularGrade + "}";
    }
}
