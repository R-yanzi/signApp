package com.sign.signin.bean;

public class Filter {

    private String name;

    private String rule;

    public Filter() {
    }

    public Filter(String name, String rule) {
        this.name = name;
        this.rule = rule;
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
     * @return rule
     */
    public String getRule() {
        return rule;
    }

    /**
     * 设置
     * @param rule
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    public String toString() {
        return "Filter{name = " + name + ", rule = " + rule + "}";
    }
}
