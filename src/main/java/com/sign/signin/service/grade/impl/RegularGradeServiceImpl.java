package com.sign.signin.service.grade.impl;

import com.sign.signin.bean.*;
import com.sign.signin.bean.grade.Ratio;
import com.sign.signin.mapper.RegularGradeMapper;
import com.sign.signin.service.grade.ProportionService;
import com.sign.signin.service.grade.RegularGradeService;
import com.sign.signin.service.report.ReportService;
import com.sign.signin.service.report.ReportUserService;
import com.sign.signin.service.report.UserService;
import com.sign.signin.service.task.TaskLogService;
import com.sign.signin.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegularGradeServiceImpl implements RegularGradeService {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportUserService reportUserService;

    @Autowired
    private RegularGradeMapper regularGradeMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private ProportionService proportionService;

    /**
     * 获取成绩列表
     * @return
     */
    @Override
    public List<Grade> getGrade() {
        // 首先获取所有学生
        List<User> users = userService.selectAll();

        // 获得所有成绩列表
        List<RegularGrade> regularGrades = regularGradeMapper.selectAll();

        List<Grade> grades = new ArrayList<>();

        // 遍历所有学生集合
        for (User user : users) {
            boolean flag = true;
            // 算考勤分
            BigDecimal attendanceScore = calculateAttendance(user.getUserid());
            // 算汇报分数
            BigDecimal reportScore = calculateReport(user.getUserid());
            // 作业分数
            BigDecimal workScore = BigDecimal.ZERO;
            for (RegularGrade regularGrade : regularGrades) {
                // 该学生已经有成绩了,保留汇报成绩,其余重新计算
                if (regularGrade.getUserid().equals(user.getUserid())) {
                    workScore = regularGrade.getWorkScore();
                    // 计算总成绩
                    BigDecimal totalScore = calculateTotal(attendanceScore, reportScore, workScore);
                    // 修改成绩
                    RegularGrade newRG = new RegularGrade(user.getUserid(), attendanceScore, reportScore, workScore, totalScore);
                    regularGradeMapper.updateByPrimaryKey(newRG);
                    grades.add(new Grade(user, newRG));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                // 没有他的汇报成绩, 则新增成绩
                // 计算总成绩
                BigDecimal totalScore = calculateTotal(attendanceScore, reportScore, workScore);
                RegularGrade newRG = new RegularGrade(user.getUserid(), attendanceScore, reportScore, workScore, totalScore);
                regularGradeMapper.insert(newRG);
                grades.add(new Grade(user, newRG));
            }
        }
        return grades;
    }

    /**
     * 计算考勤分数
     * @param userid 学号
     * @return 该学生的考勤分数
     */
    @Override
    public BigDecimal calculateAttendance(String userid) {
        // 首先获取总共的签到任务次数
        Long taskCount = taskService.getTaskCount();

        // 获取该学生签到次数
        Long userCount = taskLogService.getTaskCountByUserId(userid);

        return new BigDecimal(userCount)
                .multiply(new BigDecimal(100))
                .divide(new BigDecimal(taskCount), 2, RoundingMode.HALF_UP);
    }

    /**
     * 计算汇报分数
     * @param userid 学号
     * @return 该学生的汇报分数
     */
    @Override
    public BigDecimal calculateReport(String userid) {
        // 首先获取汇报次数
        Long totalNum = reportService.getReportCount();

        // 获取学生的所有汇报打分
        List<Weight> types = reportUserService.getWeightByUserId(userid);

        // 累加该学生获得的汇报分数
        BigDecimal total = BigDecimal.ZERO;
        for (Weight weight : types)
            total = total.add(weight.getProportion());

        // 计算分数
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal denominator = BigDecimal.valueOf(totalNum).multiply(hundred);
        return total.divide(denominator, 2, RoundingMode.HALF_UP).multiply(hundred);
    }

    /**
     * 计算总成绩
     * @param attendanceScore 考勤分数
     * @param reportScore 汇报分数
     * @param workScore 作业分数
     * @return 总成绩
     */
    @Override
    public BigDecimal calculateTotal(BigDecimal attendanceScore, BigDecimal reportScore, BigDecimal workScore) {
        BigDecimal total = BigDecimal.ZERO;
        Ratio ratio = proportionService.getRatio();
        total = total.add(attendanceScore.multiply(ratio.getAttendanceRatio()));
        total = total.add(reportScore.multiply(ratio.getReportRatio()));
        total = total.add(workScore.multiply(ratio.getWorkRatio()));
        return total;
    }

    /**
     * 修改三个比例
     * @param attendanceRatio 考勤分数占比
     * @param reportRatio 汇报分数占比
     * @param workRatio 作业分数占比
     * @throws IOException io异常
     */
    public void changeRatio(BigDecimal attendanceRatio, BigDecimal reportRatio, BigDecimal workRatio) throws IOException {
        proportionService.setRatio(new Ratio(attendanceRatio, reportRatio, workRatio));
    }
}
