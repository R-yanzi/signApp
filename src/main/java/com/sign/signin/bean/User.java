package com.sign.signin.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 */
public class User implements Serializable {
    private String userid;

    private String username;

    private String classname;



    private static final long serialVersionUID = 1L;

    public User(String userid, String username, String classname, long serialVersionUID) {
        this.userid = userid;
        this.username = username;
        this.classname = classname;
    }

    public User() {
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 设置
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String toString() {
        return "User{userid = " + userid + ", username = " + username + ", classname = " + classname + ", serialVersionUID = " + serialVersionUID + "}";
    }
}