package com.sign.signin.service.grade;

import com.sign.signin.bean.Grade;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface RegularGradeService {
    List<Grade> getGrade();

    BigDecimal calculateAttendance(String userid);

    BigDecimal calculateReport(String userid);

    BigDecimal calculateTotal(BigDecimal attendanceScore, BigDecimal reportScore, BigDecimal workScore);

    boolean changeRatio(BigDecimal attendanceRatio, BigDecimal reportRatio, BigDecimal workRatio) throws IOException;
}
