package com.sign.signin.service.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sign.signin.bean.Filter;
import com.sign.signin.bean.FilterCondition;
import com.sign.signin.bean.UserReport;

import java.util.List;

public interface FilterService{

    List<UserReport> filterUserReport(Long reportId, String filterStr) throws JsonProcessingException;

    List<FilterCondition> getFilters(Long reportId);

    List<Filter> getFilters(String filterStr) throws JsonProcessingException;
}
