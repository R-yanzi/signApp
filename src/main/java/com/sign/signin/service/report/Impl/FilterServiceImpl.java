package com.sign.signin.service.report.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sign.signin.bean.Filter;
import com.sign.signin.bean.FilterCondition;
import com.sign.signin.bean.UserReport;
import com.sign.signin.service.report.FilterService;
import com.sign.signin.service.report.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    UserReportService userReportService;

    /**
     * 根据过滤条件过滤得到学生成绩列表
     * @param reportId 汇报id
     * @param filterStr 过滤条件字符串
     * @return 过滤后的学生成绩列表
     */
    @Override
    public List<UserReport> filterUserReport(Long reportId, String filterStr) throws JsonProcessingException {
        // 使用Jackson库将得到的字符串转变成过滤条件数组
        List<Filter> filters = null;
        filters = getFilters(filterStr);

        // 根据汇报id获取所有学生成绩
        List<UserReport> userReports = userReportService.getUserReport(reportId);

        List<UserReport> filteredUserReports = new ArrayList<>();

        // 去重set
        HashSet<String> classrooms = new HashSet<>();
        HashSet<String> types = new HashSet<>();
        for (Filter filter : filters) {
            if ("班级".equals(filter.getName())) {
                classrooms.add(filter.getRule());
            } else if ("等级".equals(filter.getName())) {
                types.add(filter.getRule());
            }
        }

        // 遍历所有学生成绩, 同时进行过滤
        for (UserReport userReport : userReports) {
            boolean flag = true;
            for (Filter filter : filters) {
                if ("班级".equals(filter.getName())) {
                    if (!classrooms.contains(userReport.getUser().getClassname())) {
                        // 班级不同
                        flag = false;
                        break;
                    }
                } else if ("等级".equals(filter.getName())) {
                    if (!types.contains(userReport.getType())) {
                        // 等级不同
                        flag = false;
                        break;
                    }
                } else if ("未打分".equals(filter.getRule())) {
                    if (!userReport.getType().isEmpty() && userReport.getType() != null) {
                        // 已打分
                        flag = false;
                        break;
                    }
                } else if ("已打分".equals(filter.getRule())) {
                    if (userReport.getType().isEmpty() || userReport.getType() == null) {
                        // 未打分
                        flag = false;
                        break;
                    }
                }
            }
            if (flag)
                filteredUserReports.add(userReport);
        }
        return filteredUserReports;
    }

    /**
     * 根据汇报id获取对应的筛选条件
     * @param reportId 汇报id
     * @return 筛选条件列表
     */
    @Override
    public List<FilterCondition> getFilters(Long reportId) {
        // 获得所有汇报
        List<UserReport> userReports = userReportService.getUserReport(reportId);

        List<FilterCondition> filterConditions = new ArrayList<>();

        // 去重集合
        TreeSet<String> classrooms = new TreeSet<>();
        TreeSet<String> types = new TreeSet<>();

        // 遍历汇报成绩
        for (UserReport userReport : userReports) {
            // 添加班级
            classrooms.add(userReport.getUser().getClassname());
            if (userReport.getType() != null && !userReport.getType().isEmpty())
                types.add(userReport.getType());
        }

        if (!classrooms.isEmpty())
            filterConditions.add(new FilterCondition("班级", classrooms.stream().toList()));

        if (!types.isEmpty())
            filterConditions.add(new FilterCondition("等级", types.stream().toList()));

        List<String> station = new ArrayList<>();
        station.add("未打分");
        station.add("已打分");
        filterConditions.add(new FilterCondition("打分情况", station));
        return filterConditions;
    }

    /**
     * 使用Jackson库将得到的字符串转变成过滤条件数组
     */
    @Override
    public List<Filter> getFilters(String filterStr) throws JsonProcessingException {
        // 使用Jackson库将得到的字符串转变成过滤条件数组
        ObjectMapper objectMapper = new ObjectMapper();
        List<Filter> filters = null;
        filters = objectMapper.readValue(filterStr, new TypeReference<List<Filter>>() {});
        return filters;
    }
}
