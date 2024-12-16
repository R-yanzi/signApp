package com.sign.signin.service.grade.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sign.signin.bean.*;
import com.sign.signin.mapper.RegularGradeMapper;
import com.sign.signin.service.grade.RegularGradeService;
import com.sign.signin.service.report.ReportService;
import com.sign.signin.service.report.ReportUserService;
import com.sign.signin.service.report.UserReportService;
import com.sign.signin.service.report.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Value("${myRule.attendanceRatio}")
    private BigDecimal attendanceRatio;

    @Value("${myRule.reportRatio}")
    private BigDecimal reportRatio;

    @Value("${myRule.workRatio}")
    private BigDecimal workRatio;

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
            for (RegularGrade regularGrade : regularGrades) {
                // 该学生已经有成绩了,保留汇报成绩,其余重新计算
                if (regularGrade.getUserid().equals(user.getUserid())) {
                    grades.add(new Grade(user, attendanceScore, reportScore, regularGrade.getWorkScore()));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                // 没有他的汇报成绩
                // 作业成绩默认为0
                grades.add(new Grade(user, attendanceScore, reportScore, BigDecimal.ZERO));
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
        return BigDecimal.ZERO;
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
        System.out.println(attendanceRatio);
        System.out.println(reportRatio);
        System.out.println(workRatio);


        return BigDecimal.ZERO;
    }

    public boolean changeRatio(BigDecimal attendanceRatio, BigDecimal reportRatio, BigDecimal workRatio) throws IOException {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        String url = "src/main/resources/config/application.yml";

        // 从文件加载数据
        File file = Paths.get(url).toFile();


        Map<String, Object> data = null;
        if (url != null) {
            data = yamlMapper.readValue(file, Map.class);
            System.out.println(data);
        }
        if (attendanceRatio != null) {
            // 修改配置项
            data.put("myRule.attendanceRatio", attendanceRatio);

            // 将修改后的数据写回文件
            if (url != null) {
//                yamlMapper.writeValue(new File(url.getFile()), data);
            }
        }




        return true;
    }
}
