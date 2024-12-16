package com.sign.signin.bean;

import java.util.List;

public class ListSeries {
    private String name;
    private List<Integer> data;


    public ListSeries() {
    }

    public ListSeries(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
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
     * @return data
     */
    public List<Integer> getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String toString() {
        return "ListSeries{name = " + name + ", data = " + data + "}";
    }
}
