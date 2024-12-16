package com.sign.signin;

import com.sign.signin.bean.RegularGrade;
import com.sign.signin.bean.Report;
import com.sign.signin.config.YmlConfig;
import com.sign.signin.service.grade.RegularGradeService;
import com.sign.signin.service.report.ReportUserService;
import com.sign.signin.service.report.UserReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
class SignInApplicationTests {

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private ReportUserService reportUserService;

    @Autowired
    private RegularGradeService regularGradeService;

    @Test
    void contextLoads() throws IOException {
//        System.out.println(regularGradeService.calculateReport("202205010204"));
//        regularGradeService.calculateTotal(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
        regularGradeService.changeRatio(new BigDecimal("0.6"), new BigDecimal("0.4"), new BigDecimal("0.0"));

//        System.out.println(YmlConfig.changeRatio());
    }

}
