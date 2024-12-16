package com.sign.signin.bean.task;

import java.io.Serializable;
import lombok.Data;

/**
 * tasklog
 */
public class Tasklog implements Serializable {
    private Integer taskid;

    private Integer seatx;

    private Integer seaty;

    private String absence;

    private String userid;

    private static final long serialVersionUID = 1L;

    public Tasklog() {
    }

    public Tasklog(Integer taskid, Integer seatx, Integer seaty, String absence, String userid) {
        this.taskid = taskid;
        this.seatx = seatx;
        this.seaty = seaty;
        this.absence = absence;
        this.userid = userid;
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
     * @return seatx
     */
    public Integer getSeatx() {
        return seatx;
    }

    /**
     * 设置
     * @param seatx
     */
    public void setSeatx(Integer seatx) {
        this.seatx = seatx;
    }

    /**
     * 获取
     * @return seaty
     */
    public Integer getSeaty() {
        return seaty;
    }

    /**
     * 设置
     * @param seaty
     */
    public void setSeaty(Integer seaty) {
        this.seaty = seaty;
    }

    /**
     * 获取
     * @return absence
     */
    public String getAbsence() {
        return absence;
    }

    /**
     * 设置
     * @param absence
     */
    public void setAbsence(String absence) {
        this.absence = absence;
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

    public String toString() {
        return "Tasklog{taskid = " + taskid + ", seatx = " + seatx + ", seaty = " + seaty + ", absence = " + absence + ", userid = " + userid + "}";
    }
}