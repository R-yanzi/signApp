package com.sign.signin.service.report;

import com.sign.signin.bean.UserReport;

import java.util.List;

public interface UserReportService {
    List<UserReport> getUserReport(Long reportId);
    List<UserReport> getUserReportBySearch(Long reported, String search);
}
