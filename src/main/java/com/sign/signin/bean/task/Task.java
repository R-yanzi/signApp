package com.sign.signin.bean.task;

import java.io.Serializable;
import lombok.Data;

/**
 * task
 */
public class Task implements Serializable {
    private Integer taskid;

    private String userid;

    private String place;

    private String classname;

    private String time;

    private String state;

    private Integer courseid;

    private static final long serialVersionUID = 1L;

    public Task() {
    }

    public Task(Integer taskid, String userid, String place, String classname, String time, String state, Integer courseid) {
        this.taskid = taskid;
        this.userid = userid;
        this.place = place;
        this.classname = classname;
        this.time = time;
        this.state = state;
        this.courseid = courseid;
    }

    /**
     * 获取
     * @return taskid
     */
    public Integer getTaskid() {
        return taskid;
    }

    /**
     * 设置
     * @param taskid
     */
    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
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
     * @return place
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
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

    /**
     * 获取
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取
     * @return courseid
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 设置
     * @param courseid
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String toString() {
        return "Task{taskid = " + taskid + ", userid = " + userid + ", place = " + place + ", classname = " + classname + ", time = " + time + ", state = " + state + ", courseid = " + courseid + "}";
    }
}