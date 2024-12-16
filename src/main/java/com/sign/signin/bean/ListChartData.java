package com.sign.signin.bean;

import java.util.List;

public class ListChartData {

    private List<String> categories;

    private List<ListSeries> Series;


    public ListChartData() {
    }

    public ListChartData(List<String> categories, List<ListSeries> Series) {
        this.categories = categories;
        this.Series = Series;
    }

    /**
     * 获取
     * @return categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 设置
     * @param categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * 获取
     * @return Series
     */
    public List<ListSeries> getSeries() {
        return Series;
    }

    /**
     * 设置
     * @param Series
     */
    public void setSeries(List<ListSeries> Series) {
        this.Series = Series;
    }

    public String toString() {
        return "ListChartData{categories = " + categories + ", Series = " + Series + "}";
    }
}
