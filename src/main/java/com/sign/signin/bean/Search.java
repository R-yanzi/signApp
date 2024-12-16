package com.sign.signin.bean;

public class Search {
    // 前缀
    private String prefix;
    // 高亮
    private String highlight;
    // 后缀
    private String suffix;

    public Search() {
    }

    public Search(String prefix, String highlight, String suffix) {
        this.prefix = prefix;
        this.highlight = highlight;
        this.suffix = suffix;
    }

    /**
     * 获取
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取
     * @return highlight
     */
    public String getHighlight() {
        return highlight;
    }

    /**
     * 设置
     * @param highlight
     */
    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    /**
     * 获取
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置
     * @param suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String toString() {
        return "Search{prefix = " + prefix + ", highlight = " + highlight + ", suffix = " + suffix + "}";
    }
}
