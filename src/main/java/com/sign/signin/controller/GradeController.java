package com.sign.signin.controller;

import com.sign.signin.bean.Grade;
import com.sign.signin.bean.grade.Ratio;
import com.sign.signin.service.grade.ProportionService;
import com.sign.signin.service.grade.RegularGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private RegularGradeService regularGradeService;

    @Autowired
    private ProportionService proportionService;

    /**
     * 计算所有学生成绩并返回
     * @return 学生成绩列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Grade> getGrades() {
        // 计算并返回成绩
        return regularGradeService.getGrade();
    }

    /**
     * 改变各个成绩的占比
     * @return 是否修改成功
     */
    @RequestMapping(value = "/proportion", method = RequestMethod.PUT)
    public boolean changeRatio(@RequestBody Map<String, Object> requestBody) {

        String attendanceRatio = (String)requestBody.get("attendanceRatio");
        String reportRatio = (String)requestBody.get("reportRatio");
        String workRatio = (String)requestBody.get("workRatio");
        BigDecimal hundred = new BigDecimal(100);

        try {
            regularGradeService.changeRatio(new BigDecimal(attendanceRatio)
                    .divide(hundred, 2, RoundingMode.HALF_UP),
                    new BigDecimal(reportRatio)
                            .divide(hundred, 2, RoundingMode.HALF_UP),
                    new BigDecimal(workRatio)
                            .divide(hundred, 2, RoundingMode.HALF_UP));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取当前成绩占比返回给前端
     * @return 当前成绩占比
     */
    @RequestMapping(value = "/proportion", method = RequestMethod.GET)
    public Ratio getRatio() {
        BigDecimal hundred = new BigDecimal(100);
        Ratio ratio = proportionService.getRatio();
        return new Ratio(ratio.getAttendanceRatio().multiply(hundred), ratio.getReportRatio().multiply(hundred), ratio.getWorkRatio().multiply(hundred));
    }

    /**
     * 返回班级列表
     * @return 班级列表
     */
    @RequestMapping(value = "/classname", method = RequestMethod.GET)
    public List<String> getClassname() {
        
    }
}
