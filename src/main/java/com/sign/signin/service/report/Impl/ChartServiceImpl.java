package com.sign.signin.service.report.Impl;

import com.sign.signin.bean.*;
import com.sign.signin.service.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private UserService userService;

    @Autowired
    private WeightService weightService;

    @Autowired
    private ReportUserService reportUserService;

    @Autowired
    private UserReportService userReportService;

    /**
     * 根据汇报id获取饼图的数据
     * @param reportId 汇报id
     * @return 数据集合
     */
    @Override
    public List<PieSeries> getPieData(Long reportId) {
        // 首先获取总人数
        Long totalNum = userService.getTotalNum();

        // 等级数量映射
        HashMap<String, Integer> cnt = new HashMap<>();

        // 等级集合
        List<Weight> allWeight = weightService.getAllWeight();

        // 获取所有汇报的打分
        List<ReportUser> reportUsers = reportUserService.getReportUsersByReportId(reportId);

        // 完成等级映射计数
        for (ReportUser reportUser : reportUsers) {
            Integer weightId = reportUser.getWeightId();
            for (Weight weight : allWeight) {
                if (weight.getWeightId().equals(weightId))
                    cnt.put(weight.getType(), cnt.getOrDefault(weight.getType(), 0) + 1);
            }
        }

        List<PieSeries> pieSeriesList = new ArrayList<>();
        int num = 0;

        // 遍历等级映射, 加到最终集合中
        for (String key : cnt.keySet()) {
            pieSeriesList.add(new PieSeries(key, cnt.get(key)));
            num += cnt.get(key);
        }

        // 还有未打分人数
        if (num < totalNum)
            pieSeriesList.add(new PieSeries("未打分", (int) (totalNum - num)));

        return pieSeriesList;
    }

    /**
     * 根据饼图数据获取对应柱形图数据
     * @param reportId 汇报id
     * @return 柱形图数据
     */
    @Override
    public ListChartData getListData(Long reportId) {
        // categories集合为该次汇报的weight类型
        List<String> categories = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
        List<PieSeries> pieData = getPieData(reportId);

        for (PieSeries data : pieData) {
            // 获取种类集合
            categories.add(data.getName());
            dataList.add(data.getValue());
        }

        List<ListSeries> listSeries = new ArrayList<>();
        listSeries.add(new ListSeries("数量", dataList));

        return new ListChartData(categories, listSeries);
    }

    @Override
    public ListChartData getListDataByTypes(Long reportId, String typesStr) {
        String[] types = typesStr.split("&");

        // 班级等级映射
        HashMap<String, HashMap<String, Integer>> cnt = new HashMap<>();

        // 该次汇报的所有记录
        List<UserReport> userReports = userReportService.getUserReport(reportId);

        // 存放班级
        Set<String> categories = new TreeSet<>();
        List<ListSeries> seriesList = new ArrayList<>();

        // 进行映射
        for (UserReport userReport : userReports) {
            String classroom = userReport.getUser().getClassname();
            String type = userReport.getType();

            if (type.isEmpty())
                type = "未打分";

            HashMap<String, Integer> item = cnt.getOrDefault(classroom, new HashMap<>());
            item.put(type, item.getOrDefault(type, 0) + 1);

            cnt.put(classroom, item);
            categories.add(classroom);
        }

        // 遍历所有已选择的字符串
        for (String type : types) {
            List<Integer> temp = new ArrayList<>();
            // 遍历所有班级
            for (String classroom : categories) {
                HashMap<String, Integer> item = cnt.getOrDefault(classroom, new HashMap<>());
                item.put(type, item.getOrDefault(type, 0));
                cnt.put(classroom, item);
                temp.add(item.get(type));
            }
            seriesList.add(new ListSeries(type, temp));
        }

        return new ListChartData(categories.stream().toList(), seriesList);
    }
}
