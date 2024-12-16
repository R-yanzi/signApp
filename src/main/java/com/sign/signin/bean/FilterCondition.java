package com.sign.signin.bean;

import java.util.List;

public class FilterCondition {
    private String name;
    private List<String> rule;

    public FilterCondition() {
    }

    public FilterCondition(String name, List<String> rule) {
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
    public List<String> getRule() {
        return rule;
    }

    /**
     * 设置
     * @param rule
     */
    public void setRule(List<String> rule) {
        this.rule = rule;
    }

    public String toString() {
        return "FilterCondition{name = " + name + ", rule = " + rule + "}";
    }
}
