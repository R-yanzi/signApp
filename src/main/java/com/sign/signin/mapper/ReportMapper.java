package com.sign.signin.mapper;

import com.sign.signin.bean.Report;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportMapper {
    int deleteByPrimaryKey(Long reportId);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Long reportId);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    List<Report> selectAll();

    int updateReported(Long reportId);

    Long getReportCount();
}