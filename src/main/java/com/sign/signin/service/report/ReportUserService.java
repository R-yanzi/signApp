package com.sign.signin.service.report;

import com.sign.signin.bean.ReportUser;
import com.sign.signin.bean.User;
import com.sign.signin.bean.Weight;

import java.util.List;

public interface ReportUserService {
    List<ReportUser> getReportUsersByReportId(Long reportId);
    Boolean addReportUser(Long reportId, User user, Weight weight);
    Boolean updateReportUser(Long reportId, User user, Weight weight);
    List<Weight> getWeightByUserId(String userId);
}
