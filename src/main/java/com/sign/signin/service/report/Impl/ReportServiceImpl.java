package com.sign.signin.service.report.Impl;

import com.sign.signin.bean.Report;
import com.sign.signin.mapper.ReportMapper;
import com.sign.signin.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 返回指定顺序的汇报列表
     * @param desc 是否降序
     * @return 列表
     */
    @Override
    public List<Report> getAllReport(boolean desc) {
        List<Report> reports = reportMapper.selectAll();
        reports.sort(Comparator.comparing(Report::getReportTime));
        // 如果降序反转集合
        if (desc)
            Collections.reverse(reports);
        return reports;
    }

    /**
     * 新增汇报
     * @return 新增结果
     */
    @Transactional
    @Override
    public List<Report> addReport(boolean desc) {
        // 新增汇报记录
        int count = reportMapper.insert(new Report(null));
        if (count == 1) {
            // 新增成功
            return getAllReport(desc);
        }
        // 新增失败
        return null;
    }

    /**
     * 根据id删除对应的汇报
     * @return 删除后的结果
     */
    @Transactional
    @Override
    public List<Report> deleteReport(boolean desc, Long reportId) {
        int count = reportMapper.deleteByPrimaryKey(reportId);
        if (count == 1) {
            // 删除成功
            return getAllReport(desc);
        }
        // 删除失败
        return null;
    }

    /**
     * 根据汇报id返回汇报信息
     * @param reportId 汇报id
     * @return 汇报
     */
    @Override
    public Report getReportById(Long reportId) {
        return reportMapper.selectByPrimaryKey(reportId);
    }

    /**
     * 获取汇报次数
     * @return
     */
    @Override
    public Long getReportCount() {
        return reportMapper.getReportCount();
    }
}
