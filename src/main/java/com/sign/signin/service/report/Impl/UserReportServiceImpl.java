package com.sign.signin.service.report.Impl;

import com.sign.signin.bean.ReportUser;
import com.sign.signin.bean.User;
import com.sign.signin.bean.UserReport;
import com.sign.signin.mapper.ReportUserMapper;
import com.sign.signin.mapper.UserMapper;
import com.sign.signin.mapper.WeightMapper;
import com.sign.signin.service.report.UserReportService;
import com.sign.signin.service.report.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserReportServiceImpl implements UserReportService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReportUserMapper reportUserMapper;

    @Autowired
    private WeightMapper weightMapper;

    @Autowired
    private UserService userService;

    /**
     * 根据汇报id封装学生汇报成绩列表
     * @param reportId 汇报id
     * @return 生成汇报成绩列表
     */
    @Override
    public List<UserReport> getUserReport(Long reportId) {
        // 获取所有学生
        List<User> users = userMapper.selectAll();

        // 创建空的汇报成绩列表
        List<UserReport> userReports = new ArrayList<>();
        for (User user : users) {
            userReports.add(new UserReport(user, ""));
        }

        // 根据汇报id查找该汇报已打分同学
        List<ReportUser> reportUsers = reportUserMapper.selectByReportId(reportId);
        for (ReportUser reportUser : reportUsers) {
            // 将已打分的同学的成绩对应到上面空的汇报成绩列表
            for (UserReport userReport : userReports) {
                if (userReport.getUser().getUserid().equals(reportUser.getUserid())) {
                    // 如果学生id相同, 则修改该项的等级名字
                    userReport.setType(weightMapper.selectByPrimaryKey(reportUser.getWeightId()).getType());
                }
            }
        }
        return userReports;
    }

    @Override
    public List<UserReport> getUserReportBySearch(Long reportId, String search) {
        // 首先获取所有该次报告的学生成绩列表
        List<UserReport> userReports = getUserReport(reportId);
        // 学号集合
        List<String> ids = new ArrayList<>();
        // 学号等级映射
        Map<String, String> typeMap = new HashMap<>();
        // 遍历获得有关学号集合和学号等级映射
        for (UserReport userReport : userReports) {
            ids.add(userReport.getUser().getUserid());
            typeMap.put(userReport.getUser().getUserid(), userReport.getType() == null ? "" : userReport.getType());
        }
        // 查到的所有有关学生列表
        List<User> userInIdList = userService.getUserInIdList(ids);
        // 存放最终搜索到的内容
        List<UserReport> result = new ArrayList<>();
        // 遍历学生集合
        for (User user : userInIdList){
            UserReport userReport = new UserReport(user, typeMap.get(user.getUserid()));
            // 查姓名
            if (userReport.getUser().getUsername().contains(search)) {
                result.add(userReport);
                continue;
            }
            // 查学号
            if (userReport.getUser().getUserid().contains(search)) {
                result.add(userReport);
                continue;
            }
            // 查班级
            if (userReport.getUser().getClassname().contains(search)) {
                result.add(userReport);
                continue;
            }
            // 查成绩
            if (userReport.getType().contains(search))
                result.add(userReport);
        }
        return result;
    }
}
