package com.sign.signin.mapper;

import com.sign.signin.bean.ReportUser;
import com.sign.signin.bean.Weight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportUserMapper {
    int insert(ReportUser record);

    int insertSelective(ReportUser record);

    List<ReportUser> selectByReportId(Long reportId);

    int updateWeightId(Long reportId, Integer weightId, String userid);

    List<Weight> selectTypesByUserId(String userId);
}