package com.sign.signin.bean;

public class PieSeries {
    private String name;
    private Integer value;


    public PieSeries() {
    }

    public PieSeries(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString() {
        return "ChartData{name = " + name + ", value = " + value + "}";
    }
}
