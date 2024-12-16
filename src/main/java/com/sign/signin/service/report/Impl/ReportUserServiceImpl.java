package com.sign.signin.service.report.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sign.signin.bean.Report;
import com.sign.signin.bean.ReportUser;
import com.sign.signin.bean.User;
import com.sign.signin.bean.Weight;
import com.sign.signin.mapper.ReportMapper;
import com.sign.signin.mapper.ReportUserMapper;
import com.sign.signin.service.report.ReportUserService;
import com.sign.signin.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportUserServiceImpl implements ReportUserService {

    @Autowired
    private ReportUserMapper reportUserMapper;

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 获取汇报id的所有ReportUser
     * @param reportId 汇报id
     * @return ReportUser列表
     */
    @Override
    public List<ReportUser> getReportUsersByReportId(Long reportId) {
        return reportUserMapper.selectByReportId(reportId);
    }

    /**
     * 新增ReportUser
     * @param reportId 汇报id
     * @param user 学生
     * @param weight 等级
     * @return 是否成功
     */
    @Override
    public Boolean addReportUser(Long reportId, User user, Weight weight) {
        // 获取汇报id的所有ReportUser
        int count = reportUserMapper.insert(new ReportUser(user.getUserid(), reportId, weight.getWeightId()));
        if (count == 1) {
            // 新增成功, 同时将汇报数据表中已汇报人数新增1
            count += reportMapper.updateReported(reportId);
        }
        return count == 2;
    }

    /**
     * 根据reportId修改ReportUser
     * @param reportId 汇报id
     * @param user 学生
     * @param weight 等级
     * @return 是否成功
     */
    @Override
    public Boolean updateReportUser(Long reportId, User user, Weight weight) {
        int count = reportUserMapper.updateWeightId(reportId, weight.getWeightId(), user.getUserid());
        return count == 1;
    }

    /**
     * 根据userid获取学生的汇报等级集合
     * @param userId 学号
     * @return 汇报等级集合
     */
    @Override
    public List<Weight> getWeightByUserId(String userId) {
        return reportUserMapper.selectTypesByUserId(userId);
    }
}
