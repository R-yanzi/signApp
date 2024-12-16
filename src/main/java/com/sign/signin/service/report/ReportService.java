package com.sign.signin.service.report;

import com.sign.signin.bean.Report;

import java.util.List;

public interface ReportService {
    List<Report> getAllReport(boolean desc);

    List<Report> addReport(boolean desc);

    List<Report> deleteReport(boolean desc, Long reportId);

    Report getReportById(Long reportId);

    Long getReportCount();
}
